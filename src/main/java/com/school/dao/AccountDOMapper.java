package com.school.dao;

import com.school.domain.AccountDO;
import org.springframework.stereotype.Repository;

public interface AccountDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    AccountDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);
}
