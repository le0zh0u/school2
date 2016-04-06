package com.school.service.impl;

import com.school.dao.AccountDOMapper;
import com.school.dao.AccountInfoDOMapper;
import com.school.domain.AccountDO;
import com.school.domain.AccountInfoDO;
import com.school.enums.StatusEnum;
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
    @Autowired
    private AccountInfoDOMapper accountInfoDOMapper;

    public AccountDO getAccountDOById(Integer id) {
        AccountDO accountDO = accountDOMapper.selectByPrimaryKey(id);
        return accountDO;
    }

    public void registerAccount(String loginName, String password, String stuCode, String name, String universityCode) {

        //初始化AccountDO
        AccountDO accountDO = new AccountDO();
        accountDO.setLoginName(loginName);
        accountDO.setPassword(password);
        accountDO.setStatus(StatusEnum.ACTIVE.getCode());

        //初始化AccountInfoDO
        AccountInfoDO accountInfoDO = new AccountInfoDO();
        accountInfoDO.setStuCode(stuCode);
        accountInfoDO.setName(name);
        accountInfoDO.setUniversityCode(universityCode);

        //插入accountInfo中
        accountInfoDOMapper.insert(accountInfoDO);
        //插入AccountDO中
        accountDO.setUserId(accountInfoDO.getId());
        accountDOMapper.insert(accountDO);

    }
}
