package com.school.service;

import com.school.dto.upstream.MessageAddDto;
import com.school.dto.upstream.MessageItemDto;
import com.school.dto.upstream.WatchedMessageItemDto;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public interface MessageService {

    List<MessageItemDto> findMessageListByUserId(Integer userId);

    /**
     * 获取关注的话题
     *
     * @param accountId
     * @return
     */
    List<WatchedMessageItemDto> findWatchedMessageByAccountId(Integer accountId);

    /**
     * 通过用户id和消息类型获取消息列表
     *
     * @param accountId
     * @param typeId
     * @return
     */
    List<MessageItemDto> findMessageListByAccountAndType(Integer accountId, Integer typeId);

    /**
     * 根据messageId和accountId 删除消息
     *
     * @param messageId
     * @param accountId
     * @return
     */
    int deleteMessage(Integer messageId, Integer accountId);

    /**
     * 添加message
     * @param messageAddDto
     * @return
     */
    MessageAddDto addMessage(MessageAddDto messageAddDto);
}
