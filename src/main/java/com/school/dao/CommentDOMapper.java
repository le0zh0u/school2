package com.school.dao;

import com.school.domain.CommentDO;
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
    List<CommentDO> findCommentListByMessageIdList(@Param("messageIdList") List<Integer> messageIdList);
}
