package com.school.controller;

import com.school.domain.AccountDO;
import com.school.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/")
    @ResponseBody
    public AccountDO getAccountDO() {
        logger.info("start get account do ");
        return accountService.getAccountDOById(1);
    }
}
