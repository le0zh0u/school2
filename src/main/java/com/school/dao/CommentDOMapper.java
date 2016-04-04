package com.school.dao;

import com.school.domain.CommentDO;
import com.school.dto.MessageCriticsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentDO record);

    int insertSelective(CommentDO record);

    CommentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentDO record);

    int updateByPrimaryKey(CommentDO record);

    /**
     * 通过messageId获取评论
     *
     * @param messageIdList
     * @return
     */
    List<MessageCriticsDto> findCommentListByMessageIdList(@Param("messageIdList") List<Integer> messageIdList);

    /**
     * 通过userid获取未读的评论
     *
     * @param userId
     * @return
     */
    List<MessageCriticsDto> findUnReadCommentListByUserId(@Param("userId") Integer userId);

    /**
     * 通过用户id获取用户关注的消息id列表
     *
     * @param accountId
     * @return
     */
    List<Integer> findWatchedMessageIdListByUserId(@Param("accountId") Integer accountId);
}
