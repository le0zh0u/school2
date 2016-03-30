package com.school.service.impl;

import com.school.dao.AccountInfoDOMapper;
import com.school.domain.AccountInfoDO;
import com.school.dto.BizResult;
import com.school.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouchunjie on 16/3/30.
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    private static final Logger logger = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoDOMapper accountInfoDOMapper;

    public BizResult<AccountInfoDO> getAccountInfoDOByUserId(Integer userId) {
        logger.info("start get account info do by user id");
        BizResult<AccountInfoDO> resultBiz = new BizResult<AccountInfoDO>();
        try {
            AccountInfoDO accountInfoDO = accountInfoDOMapper.selectByPrimaryKey(userId);
            resultBiz.setData(accountInfoDO);

        } catch (Exception e) {
            logger.error("get account info DO failed, exception is {}", e);
            resultBiz.setException(e);
        }

        return resultBiz;
    }
}
