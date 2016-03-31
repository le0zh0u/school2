package com.school.dao;

import com.school.domain.MessageImageRelationDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageImageRelationDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageImageRelationDO record);

    int insertSelective(MessageImageRelationDO record);

    MessageImageRelationDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageImageRelationDO record);

    int updateByPrimaryKey(MessageImageRelationDO record);

    List<MessageImageRelationDO> findMessageImageRelationListByMessageIdList(@Param("messageIdList") List<Integer> ids);
}
