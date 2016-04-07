package com.school.controller;

import com.school.domain.AccountDO;
import com.school.domain.AccountInfoDO;
import com.school.dto.MessageCriticsDto;
import com.school.dto.upstream.AccountInformationDto;
import com.school.dto.upstream.BizResult;
import com.school.dto.upstream.WatchedMessageItemDto;
import com.school.enums.BizResultEnum;
import com.school.service.AccountInfoService;
import com.school.service.AccountService;
import com.school.service.CommentService;
import com.school.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private CommentService commentService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    @ResponseBody
    public AccountDO getAccountDO() {
        logger.info("start get account do ");
        return accountService.getAccountDOById(1);
    }

    /**
     * 注册用户
     *
     * @param loginName
     * @param password
     * @param stuCode
     * @param name
     * @param universityCode
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BizResult<String> registerAccount(String loginName, String password, String stuCode, String name,
            String universityCode) {
        logger.info("start register account");
        BizResult<String> result = new BizResult<String>();
        try {
            accountService.registerAccount(loginName, password, stuCode, name, universityCode);
            result.success();
        } catch (Exception e) {
            logger.error("register account failed.", e);
            result.setException(e);
        }

        return result;

    }

    /**
     * 更新用户信息
     * @param accountInfoDO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public BizResult<String> updateAccount(@RequestBody AccountInfoDO accountInfoDO) {
        logger.info("start update  Account");
        BizResult<String> result = new BizResult<String>();
        try {
            if (accountInfoDO.getId() == null) {
                result.setFailed(BizResultEnum.MISSING_PARAMS);
                return result;
            }

            accountInfoService.updateAccountInfo(accountInfoDO);
            result.success();
        } catch (Exception e) {
            result.setException(e);
        }
        return result;
    }

    /**
     * 获取用户信息
     *
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

    /**
     * 获取个人未读评论
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "unread/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public BizResult<List<MessageCriticsDto>> getUserComments(@PathVariable("accountId") Integer accountId) {
        logger.info("get user comment , user id is {}", accountId);
        BizResult<List<MessageCriticsDto>> result = new BizResult<List<MessageCriticsDto>>();

        try {
            List<MessageCriticsDto> criticsDtoList = commentService.findUnReadCommentListByUserId(accountId);

            result.setData(criticsDtoList);
        } catch (Exception e) {
            logger.error("get user unread comment failed.", e);
            result.setException(e);
        }

        return result;
    }

    /**
     * 获取关注的话题
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/watch/{accountId}")
    @ResponseBody
    public BizResult<List<WatchedMessageItemDto>> getUserWatchMessage(@PathVariable("accountId") Integer accountId) {
        logger.info("get user watch message , user id is {}", accountId);

        BizResult<List<WatchedMessageItemDto>> result = new BizResult<List<WatchedMessageItemDto>>();

        try {

            List<WatchedMessageItemDto> watchMessages = messageService.findWatchedMessageByAccountId(accountId);

            result.setData(watchMessages);
        } catch (Exception e) {
            logger.error("get user watch message failed", e);
            result.setException(e);
        }

        return result;
    }


}
