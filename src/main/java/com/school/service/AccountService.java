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

}
