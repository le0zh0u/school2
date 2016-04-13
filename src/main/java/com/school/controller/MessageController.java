package com.school.controller;

import com.school.dto.upstream.BizResult;
import com.school.dto.upstream.MessageItemDto;
import com.school.enums.BizResultEnum;
import com.school.service.MessageService;
import com.school.util.FileUploadUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
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


    @RequestMapping("/pic")
    @ResponseBody
    public BizResult<String> addPic(@RequestParam("photo") MultipartFile file, HttpServletRequest request) {

        BizResult<String> result = new BizResult<String>();
        try {
            String filePath = FileUploadUtil.uploadFile(file, request);
            logger.info("filePath:" + filePath);
            //            response.setContentType("text/html;charset=utf8");
            //            response.getWriter().write("<img src='" + filePath + "'/>");
            result.setData(filePath);
        } catch (Exception e) {
            result.setException(e);
        }
        return result;
    }

    @RequestMapping("/getPic")
    public void getPic(String fileName, HttpServletResponse response) throws Exception {
        OutputStream os = response.getOutputStream();
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("image/jpeg; charset=utf-8");
            os.write(FileUtils.readFileToByteArray(FileUploadUtil.getFile(fileName)));
            os.flush();

        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public BizResult<String> addMessage() {
        logger.info("start add message");
        BizResult<String> result = new BizResult<String>();



        return result;
    }

    /**
     * 查看自己发的消息以及他人评论等
     *
     * @param accountId
     * @return
     */
    @RequestMapping("/history/{accountId}")
    @ResponseBody
    public BizResult<List<MessageItemDto>> getHistoryMessageList(@PathVariable("accountId") Integer accountId) {
        logger.info("start get message list by userId");
        BizResult<List<MessageItemDto>> result = new BizResult<List<MessageItemDto>>();
        try {
            List<MessageItemDto> messageList = messageService.findMessageListByUserId(accountId);

            result.setData(messageList);
        } catch (Exception e) {
            logger.error("get history message list failed.", e);
            result.setException(e);
        }

        return result;
    }

    /**
     * 通过类别和用户返回圈子内容
     *
     * @param accountId
     * @param typeId
     * @return
     */
    @RequestMapping("/{typeId}/{accountId}")
    @ResponseBody
    public BizResult<List<MessageItemDto>> getMessageListByAccountAndType(@PathVariable() Integer accountId,
            @PathVariable Integer typeId) {
        logger.info("start get message list by account and type");
        BizResult<List<MessageItemDto>> result = new BizResult<List<MessageItemDto>>();
        try {
            List<MessageItemDto> messageItemDtoList = messageService.findMessageListByAccountAndType(accountId, typeId);
            result.setData(messageItemDtoList);
        } catch (Exception e) {
            logger.error("get message list by account and type", e);
            result.setException(e);
        }
        return result;
    }

    /**
     * 删除消息
     *
     * @param messageId
     * @param accountId
     * @return
     */
    @RequestMapping("/del/{accountId}/{messageId}")
    @ResponseBody
    public BizResult<String> deleteMessage(@PathVariable Integer messageId, @PathVariable Integer accountId) {
        logger.info("start delete message by id");
        BizResult<String> result = new BizResult<String>();
        try {
            int delLines = messageService.deleteMessage(messageId, accountId);
            if (delLines > 0) {
                result.success();
            } else {
                result.setFailed(BizResultEnum.DEL_FAILED);
            }
        } catch (Exception e) {
            logger.error("delete message by id failed, messageId is {}", messageId, e);
            result.setException(e);
        }

        return result;
    }
}
