package com.school.enums;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public enum BizResultEnum {
    SUCCESS("SUCCESS","SUCCESS"),
    EXCEPTION_ERROR("SYSTEM_ERROR","system_error");

    private String code;
    private String msg;

    BizResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
