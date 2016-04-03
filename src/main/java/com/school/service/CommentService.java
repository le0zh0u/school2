package com.school.service;

import com.school.dto.MessageCriticsDto;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public interface CommentService {

    /**
     * 通过userId获取用户未读评论
     *
     * @param userId
     * @return
     */
    public List<MessageCriticsDto> findUnReadCommentListByUserId(Integer userId);
}
