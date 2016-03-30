package com.school.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhouchunjie on 16/3/30.
 */
public enum MajorEnum {
    CS("ComputerScience","计算机科学与技术"),
    SW("SoftWare","软件工程");


    private String code;
    private String name;

    MajorEnum(String code, String name) {
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
        for (MajorEnum majorEnum : MajorEnum.values()) {
            if (majorEnum.getCode().equals(code)) {
                return majorEnum.getName();
            }
        }
        return StringUtils.EMPTY;
    }
}
