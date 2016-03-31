package com.school.service.impl;

import com.school.dao.CommentDOMapper;
import com.school.dao.MessageDOMapper;
import com.school.dao.MessageImageRelationDOMapper;
import com.school.domain.CommentDO;
import com.school.domain.MessageDO;
import com.school.domain.MessageImageRelationDO;
import com.school.dto.upstream.MessageItemDto;
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
        List<MessageImageRelationDO> messageImageRelationDOList =
                messageImageRelationDOMapper.findMessageImageRelationListByMessageIdList(messageIdList);

        //将list转成map,key为messageId, value为imageUrl
        Map<Integer, List<String>> messageImageRelationMap = new HashMap<Integer, List<String>>();
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

        //通过messageIdList 获取评论
        List<CommentDO> commentDOList = commentDOMapper.findCommentListByMessageIdList(messageIdList);

        //将commentList转换成map
        Map<Integer, List<CommentDO>> commentDOMap = new HashMap<Integer, List<CommentDO>>();
        if (CollectionUtils.isNotEmpty(commentDOList)) {
            for (CommentDO commentDO : commentDOList) {
                List<CommentDO> list = commentDOMap.get(commentDO.getMessageId());
                if (CollectionUtils.isEmpty(list)) {
                    list = new ArrayList<CommentDO>();
                }
                list.add(commentDO);

                commentDOMap.put(commentDO.getMessageId(), list);
            }
        }

        int likeCount = 0;
        int watchCount = 0;

        //遍历messageList 转换成 messageItemDto
        for (MessageDO messageDO : messageDOList) {
            MessageItemDto messageItemDto = new MessageItemDto();

            messageItemDto.setId(messageDO.getId());
            messageItemDto.setUserId(messageDO.getUserId());
            messageItemDto.setContent(messageDO.getContent());
            messageItemDto.setStatus(messageDO.getStatus());
            messageItemDto.setType(messageDO.getType());

            List<String> imageUrlList = messageImageRelationMap.get(messageDO.getId());
            if (imageUrlList == null) {
                imageUrlList = new ArrayList<String>();
            }
            messageItemDto.setImageList(imageUrlList);

            // 设置返回的红心,关注以及评论
            List<CommentDO> commentDOs = commentDOMap.get(messageDO.getId());
            List<CommentDO> commentDOsTemp = new ArrayList<CommentDO>();
            if (commentDOs != null && CollectionUtils.isNotEmpty(commentDOs)){
                for(CommentDO commentDO : commentDOs){
                    if (commentDO.getType().equals(CommentTypeEnum.LIKE.getCode())){
                        likeCount ++;
                    }else if (commentDO.getType().equals(CommentTypeEnum.WATCH.getCode())){
                        watchCount++;
                    }else if(commentDO.getType().equals(CommentTypeEnum.COMMENT.getCode())){
                        commentDOsTemp.add(commentDO);
                    }
                }
            }
            messageItemDto.setCommentCount(commentDOsTemp.size());
            messageItemDto.setCommentList(commentDOsTemp);
            messageItemDto.setLikeCount(likeCount);
            messageItemDto.setWatchCount(watchCount);

            messageItemDtoList.add(messageItemDto);
        }

        return messageItemDtoList;
    }
}
