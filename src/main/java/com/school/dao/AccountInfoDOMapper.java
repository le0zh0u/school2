package com.school.dao;

import com.school.domain.AccountInfoDO;

public interface AccountInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfoDO record);

    int insertSelective(AccountInfoDO record);

    AccountInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountInfoDO record);

    int updateByPrimaryKey(AccountInfoDO record);
}