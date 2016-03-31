package com.school.dao;

import com.school.domain.MessageDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageDO record);

    int insertSelective(MessageDO record);

    MessageDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageDO record);

    int updateByPrimaryKey(MessageDO record);

    /**
     * 根据用户Id获取发送的消息
     *
     * @param userId
     * @return
     */
    List<MessageDO> findMessageListByUserId(@Param("userId") Integer userId);
}
