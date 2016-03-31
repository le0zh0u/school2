package com.school.service;

import com.school.dto.upstream.MessageItemDto;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public interface MessageService {

    List<MessageItemDto> findMessageListByUserId(Integer userId);

}
