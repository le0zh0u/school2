package com.school.service;

import com.school.domain.AccountInfoDO;
import com.school.dto.BizResult;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public interface AccountInfoService {

    /**
     * 通过用户Id获取用户基本信息
     *
     * @param userId
     * @return
     */
    BizResult<AccountInfoDO> getAccountInfoDOByUserId(Integer userId);
}
