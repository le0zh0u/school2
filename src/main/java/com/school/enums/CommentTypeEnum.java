package com.school.enums;

/**
 * Created by zhouchunjie on 16/3/31.
 */
public enum CommentTypeEnum {
    COMMENT(1, "评论"),
    LIKE(2, "喜欢"),
    WATCH(3, "关注");

    private Integer code;
    private String desc;


    CommentTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
