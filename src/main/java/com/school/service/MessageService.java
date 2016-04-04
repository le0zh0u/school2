package com.school.service;

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
}
