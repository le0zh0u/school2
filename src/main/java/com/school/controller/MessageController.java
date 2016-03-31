package com.school.controller;

import com.school.dto.upstream.BizResult;
import com.school.dto.upstream.MessageItemDto;
import com.school.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhouchunjie on 16/3/31.
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping("/list/{userId}")
    @ResponseBody
    public BizResult<List<MessageItemDto>> getMessageList(@PathVariable("userId") Integer userId) {
        logger.info("start get message list by userId");
        BizResult<List<MessageItemDto>> result = new BizResult<List<MessageItemDto>>();
        try {
            List<MessageItemDto> messageList = messageService.findMessageListByUserId(userId);

            result.setData(messageList);
        } catch (Exception e) {
            logger.error("get message list failed.", e);
            result.setException(e);
        }

        return result;
    }
}
