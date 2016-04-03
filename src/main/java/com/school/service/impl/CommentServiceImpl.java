package com.school.service.impl;

import com.school.dao.CommentDOMapper;
import com.school.dto.MessageCriticsDto;
import com.school.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/30.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDOMapper commentDOMapper;

    public List<MessageCriticsDto> findUnReadCommentListByUserId(Integer userId) {

        return commentDOMapper.findUnReadCommentListByUserId(userId);
    }
}
