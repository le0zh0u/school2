package com.school.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public enum UniversityEnum {

    ZJUT("ZJUT", "浙江工业大学"),
    ZJU("ZJU", "浙江大学");

    private String code;
    private String name;

    UniversityEnum(String code, String name) {
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
        for (UniversityEnum universityEnum : UniversityEnum.values()) {
            if (universityEnum.getCode().equals(code)) {
                return universityEnum.getName();
            }
        }
        return StringUtils.EMPTY;
    }
}
