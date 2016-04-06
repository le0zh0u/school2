package com.school.service;

import com.school.domain.AccountInfoDO;
import com.school.dto.upstream.AccountInformationDto;

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
    AccountInformationDto getAccountInfoDOByUserId(Integer userId);

    /**
     * 修改用户基本信息
     *
     * @param accountInfoDO
     */
    void updateAccountInfo(AccountInfoDO accountInfoDO) throws Exception;
}
