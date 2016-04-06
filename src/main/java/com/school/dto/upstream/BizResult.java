package com.school.dto.upstream;

import com.school.enums.BizResultEnum;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public class BizResult<T> {

    private String code;
    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.code = BizResultEnum.SUCCESS.getCode();
        this.msg = BizResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public void setFailed(BizResultEnum result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    public void setException(Exception e) {
        this.code = BizResultEnum.EXCEPTION_ERROR.getCode();
        this.msg = e.getMessage();
    }

    public void success() {
        this.code = BizResultEnum.SUCCESS.getCode();
        this.msg = BizResultEnum.SUCCESS.getMsg();
    }

}
