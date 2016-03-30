package com.school.service.impl;

import com.school.dao.AccountDOMapper;
import com.school.domain.AccountDO;
import com.school.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouchunjie on 16/3/29.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDOMapper accountDOMapper;

    public AccountDO getAccountDOById(Integer id) {
        AccountDO accountDO = accountDOMapper.selectByPrimaryKey(id);
        return accountDO;
    }
}
