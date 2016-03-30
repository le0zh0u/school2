package com.school.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public enum CollegeEnum {
    COMPUTER("COMPUTER", "计算机学院");

    private String code;
    private String name;

    CollegeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return StringUtils.EMPTY;
        }
        for (CollegeEnum collegeEnum : CollegeEnum.values()) {
            if (collegeEnum.getCode().equals(code)) {
                return collegeEnum.getName();
            }
        }
        return StringUtils.EMPTY;
    }
}
