package com.school.dao;

import com.school.domain.MessageDO;
import com.school.dto.upstream.WatchedMessageItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDOMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByMessageIdAndUserId(@Param("messageId") Integer messageId, @Param("userId") Integer userId);

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

    /**
     * 通过
     *
     * @param watchedMessageIdList
     * @return
     */
    List<WatchedMessageItemDto> findWatchMessageByMessageIdList(
            @Param("messageIdList") List<Integer> watchedMessageIdList);

    /**
     * 通过高校编号和类型获取消息列表
     *
     * @param universityCode
     * @param typeId
     * @return
     */
    List<MessageDO> findMessageListByUniversityAndType(@Param("universityCode") String universityCode, @Param("typeId") Integer typeId);
}
