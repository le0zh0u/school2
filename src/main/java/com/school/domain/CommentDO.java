package com.school.domain;

import java.util.Date;

public class CommentDO {
    private Integer id;

    private Integer messageId;

    private Integer criticsId;

    private Integer status;

    private Integer type;

    private Integer markReak;

    private Date createTime;

    private Date modifyTime;

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

    public Integer getMarkReak() {
        return markReak;
    }

    public void setMarkReak(Integer markReak) {
        this.markReak = markReak;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}