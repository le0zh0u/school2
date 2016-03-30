package com.school.enums;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public enum BizResultEnum {
    SUCCESS("SUCCESS", "成功"),
    EXCEPTION_ERROR("SYSTEM_ERROR", "系统错误"),

    MISSING_PARAMS("MISSING_PARAMS", "缺少参数");

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
