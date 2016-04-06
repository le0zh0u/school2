package com.school.service;

import com.school.domain.AccountDO;

/**
 * Created by zhouchunjie on 16/3/29.
 */
public interface AccountService {

    /**
     * 通过主键id获取账户信息
     *
     * @param id
     * @return
     */
    AccountDO getAccountDOById(Integer id);

    /**
     * 注册新用户
     *
     * @param loginName
     * @param password
     * @param stuCode
     * @param name
     * @param universityCode
     * @return
     */
    void registerAccount(String loginName, String password, String stuCode, String name,
            String universityCode);
}
