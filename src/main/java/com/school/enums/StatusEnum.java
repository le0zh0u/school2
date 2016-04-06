package com.school.enums;

/**
 * Created by zhouchunjie on 16/4/6.
 */
public enum StatusEnum {
    ACTIVE(1,"有效"),DELETE(2,"删除");

    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
