package com.school.service.impl;

import com.school.dao.AccountInfoDOMapper;
import com.school.dao.CommentDOMapper;
import com.school.dao.MessageDOMapper;
import com.school.dao.MessageImageRelationDOMapper;
import com.school.domain.AccountInfoDO;
import com.school.domain.MessageDO;
import com.school.domain.MessageImageRelationDO;
import com.school.dto.MessageCriticsDto;
import com.school.dto.upstream.MessageItemDto;
import com.school.dto.upstream.WatchedMessageItemDto;
import com.school.enums.CommentTypeEnum;
import com.school.service.MessageService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouchunjie on 16/3/30.
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageDOMapper messageDOMapper;
    @Autowired
    private MessageImageRelationDOMapper messageImageRelationDOMapper;
    @Autowired
    private CommentDOMapper commentDOMapper;
    @Autowired
    private AccountInfoDOMapper accountInfoDOMapper;

    public List<MessageItemDto> findMessageListByUserId(Integer userId) {

        List<MessageItemDto> messageItemDtoList = new ArrayList<MessageItemDto>();

        //根据用户Id获取消息
        List<MessageDO> messageDOList = messageDOMapper.findMessageListByUserId(userId);

        if (CollectionUtils.isEmpty(messageDOList)) {
            //无message, 直接返回空数据
            return messageItemDtoList;
        }
        //遍历messageDOList,获取所有messageId,用以搜索message图片
        List<Integer> messageIdList = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(messageDOList)) {
            for (MessageDO messageDO : messageDOList) {
                messageIdList.add(messageDO.getId());
            }
        }

        //通过messageIdList获取message图片
        Map<Integer, List<String>> messageImageRelationMap = getMessageImageMap(messageIdList);

        //通过messageIdList 获取评论
        List<MessageCriticsDto> commentDOList = commentDOMapper.findCommentListByMessageIdList(messageIdList);

        //将commentList转换成map
        Map<Integer, List<MessageCriticsDto>> commentDOMap = new HashMap<Integer, List<MessageCriticsDto>>();
        if (CollectionUtils.isNotEmpty(commentDOList)) {
            for (MessageCriticsDto commentDO : commentDOList) {
                List<MessageCriticsDto> list = commentDOMap.get(commentDO.getMessageId());
                if (CollectionUtils.isEmpty(list)) {
                    list = new ArrayList<MessageCriticsDto>();
                }
                list.add(commentDO);

                commentDOMap.put(commentDO.getMessageId(), list);
            }
        }

        messageItemDtoList = convertMessageDO2MessageItemDto(messageDOList, messageImageRelationMap, commentDOMap);

        return messageItemDtoList;
    }

    private List<MessageItemDto> convertMessageDO2MessageItemDto(List<MessageDO> messageDOList,
            Map<Integer, List<String>> messageImageRelationMap, Map<Integer, List<MessageCriticsDto>> commentDOMap) {
        List<MessageItemDto> list = new ArrayList<MessageItemDto>();
        if (CollectionUtils.isEmpty(messageDOList)) {
            return list;
        }
        //遍历messageList 转换成 messageItemDto
        for (MessageDO messageDO : messageDOList) {
            int likeCount = 0;
            int watchCount = 0;

            MessageItemDto messageItemDto = new MessageItemDto();

            messageItemDto.setId(messageDO.getId());
            messageItemDto.setUserId(messageDO.getUserId());
            messageItemDto.setContent(messageDO.getContent());
            messageItemDto.setStatus(messageDO.getStatus());
            messageItemDto.setType(messageDO.getType());

            List<String> imageUrlList = new ArrayList<String>();
            if (messageImageRelationMap != null) {
                imageUrlList = messageImageRelationMap.get(messageDO.getId());
                if (imageUrlList == null) {
                    imageUrlList = new ArrayList<String>();
                }
            }
            messageItemDto.setImageList(imageUrlList);
            // 设置返回的红心,关注以及评论
            List<MessageCriticsDto> commentDOsTemp = new ArrayList<MessageCriticsDto>();
            if (commentDOMap != null) {
                List<MessageCriticsDto> commentDOs = commentDOMap.get(messageDO.getId());
                if (commentDOs != null && CollectionUtils.isNotEmpty(commentDOs)) {
                    for (MessageCriticsDto commentDO : commentDOs) {
                        if (commentDO.getType().equals(CommentTypeEnum.LIKE.getCode())) {
                            likeCount++;
                        } else if (commentDO.getType().equals(CommentTypeEnum.WATCH.getCode())) {
                            watchCount++;
                        } else if (commentDO.getType().equals(CommentTypeEnum.COMMENT.getCode())) {
                            commentDOsTemp.add(commentDO);
                        }
                    }
                }
                messageItemDto.setCommentCount(commentDOsTemp.size());
            } else {
                messageItemDto.setCommentCount(0);
            }
            messageItemDto.setCommentList(commentDOsTemp);
            messageItemDto.setLikeCount(likeCount);
            messageItemDto.setWatchCount(watchCount);

            messageItemDto.setUniversityCode(messageDO.getUniversityCode());

            list.add(messageItemDto);
        }

        return list;
    }

    private Map<Integer, List<String>> getMessageImageMap(List<Integer> messageIdList) {
        Map<Integer, List<String>> messageImageRelationMap = new HashMap<Integer, List<String>>();

        if (CollectionUtils.isEmpty(messageIdList)) {
            return messageImageRelationMap;
        }
        //通过messageIdList获取message图片
        List<MessageImageRelationDO> messageImageRelationDOList =
                messageImageRelationDOMapper.findMessageImageRelationListByMessageIdList(messageIdList);

        //将list转成map,key为messageId, value为imageUrl
        if (CollectionUtils.isNotEmpty(messageImageRelationDOList)) {
            for (MessageImageRelationDO messageImageRelationDO : messageImageRelationDOList) {
                List<String> imageList = messageImageRelationMap.get(messageImageRelationDO.getMessageId());
                if (imageList == null) {
                    imageList = new ArrayList<String>();
                }
                imageList.add(messageImageRelationDO.getImageThumbnailUrl());
                messageImageRelationMap.put(messageImageRelationDO.getMessageId(), imageList);
            }
        }

        return messageImageRelationMap;
    }

    public List<WatchedMessageItemDto> findWatchedMessageByAccountId(Integer accountId) {

        //通过用户id,获取已关注的message
        List<Integer> watchedMessageIdList = commentDOMapper.findWatchedMessageIdListByUserId(accountId);

        if (CollectionUtils.isEmpty(watchedMessageIdList)) {
            //无关注话题
            return new ArrayList<WatchedMessageItemDto>();
        }

        //通过messageIdList获取完整的message
        List<WatchedMessageItemDto> result = messageDOMapper.findWatchMessageByMessageIdList(watchedMessageIdList);

        //通过messageIdList获取message图片
        Map<Integer, List<String>> messageImageRelationMap = getMessageImageMap(watchedMessageIdList);

        //遍历messageList,放入图片
        for (WatchedMessageItemDto watchedMessageItemDto : result) {
            List<String> imageList = messageImageRelationMap.get(watchedMessageItemDto.getId());
            if (CollectionUtils.isEmpty(imageList)) {
                continue;
            }
            watchedMessageItemDto.setImageList(imageList);
        }
        return result;
    }

    public List<MessageItemDto> findMessageListByAccountAndType(Integer accountId, Integer typeId) {

        List<MessageItemDto> list = new ArrayList<MessageItemDto>();

        //获取account信息
        AccountInfoDO accountInfoDO = accountInfoDOMapper.selectByPrimaryKey(accountId);
        if (accountInfoDO == null) {
            return list;
        }

        //获取用户所在大学code
        String universityCode = accountInfoDO.getUniversityCode();

        //通过大学code和消息类型获取消息列表
        List<MessageDO> memberDOList = messageDOMapper.findMessageListByUniversityAndType(universityCode, typeId);
        if (CollectionUtils.isEmpty(memberDOList)) {
            return list;
        }

        //获取符合条件的messageIdList
        List<Integer> messageIdList = new ArrayList<Integer>();
        for (MessageDO messageDO : memberDOList) {
            messageIdList.add(messageDO.getId());
        }

        //通过messageIdList获取message图片
        Map<Integer, List<String>> messageImageRelationMap = getMessageImageMap(messageIdList);

        //将memberDO转成memberItemDto
        list = convertMessageDO2MessageItemDto(memberDOList, messageImageRelationMap, null);

        return list;
    }

    public int deleteMessage(Integer messageId, Integer accountId) {

        int delLines = messageDOMapper.deleteByMessageIdAndUserId(messageId, accountId);

        if (delLines > 0) {
            //删除成功,删除relation
            messageImageRelationDOMapper.deleteByMessageId(messageId);
        }
        return delLines;
    }
}
