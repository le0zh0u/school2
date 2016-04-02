package com.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by zhouchunjie on 16/4/1.
 */
public class MessageCriticsDto {

    private String criticsNickName;

    private String criticsHeadPortrait;

    @JsonIgnore
    private Integer id;

    private Integer messageId;

    private Integer criticsId;

    private String content;

    @JsonIgnore
    private Integer status;

    private Integer type;

    @JsonIgnore
    private Integer markRead;

    public String getCriticsNickName() {
        return criticsNickName;
    }

    public void setCriticsNickName(String criticsNickName) {
        this.criticsNickName = criticsNickName;
    }

    public String getCriticsHeadPortrait() {
        return criticsHeadPortrait;
    }

    public void setCriticsHeadPortrait(String criticsHeadPortrait) {
        this.criticsHeadPortrait = criticsHeadPortrait;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getCriticsId() {
        return criticsId;
    }

    public void setCriticsId(Integer criticsId) {
        this.criticsId = criticsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMarkRead() {
        return markRead;
    }

    public void setMarkRead(Integer markRead) {
        this.markRead = markRead;
    }
}
