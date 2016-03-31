package com.school.controller;

import com.school.domain.AccountDO;
import com.school.dto.upstream.AccountInformationDto;
import com.school.dto.upstream.BizResult;
import com.school.enums.BizResultEnum;
import com.school.service.AccountInfoService;
import com.school.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhouchunjie on 16/3/29.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountInfoService accountInfoService;

    @RequestMapping("/")
    @ResponseBody
    public AccountDO getAccountDO() {
        logger.info("start get account do ");
        return accountService.getAccountDOById(1);
    }

    /**
     * 获取用户信息
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/info/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public BizResult<AccountInformationDto> getAccountInfo(@PathVariable("accountId") Integer accountId) {
        logger.info("get account info , user id is {} ", accountId);
        BizResult<AccountInformationDto> result = new BizResult<AccountInformationDto>();
        if (accountId == null) {
            result.setFailed(BizResultEnum.MISSING_PARAMS);
            return result;
        }
        try {
            AccountInformationDto accountInfoDO = accountInfoService.getAccountInfoDOByUserId(accountId);
            result.setData(accountInfoDO);
        } catch (Exception e) {
            logger.error("get account info failed", e);
            result.setException(e);
        }
        return result;
    }


}
