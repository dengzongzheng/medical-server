package com.dzz.medical.common.enums;

import java.util.Objects;

/**
 * 机构类别信息枚举
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:28
 */
public enum  OrganizationEnum {

    PUBLIC(1, "公共机构"),
    SCHOOL(2, "学校"),
    MEDICAL(3, "医疗机构"),
    WATER_SUPPLY(4, "供水单位"),
    SUPERVISORY(5,"监督协管");

    private Integer code;

    private String name;

    OrganizationEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(Integer code) {
        for (OrganizationEnum status : OrganizationEnum.values()) {
            if (Objects.equals(code, status.getCode())) {
                return status.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name) {
        for (OrganizationEnum type : OrganizationEnum.values()) {
            if (Objects.equals(name, type.getName())) {
                return type.getCode();
            }
        }
        return null;
    }
}
