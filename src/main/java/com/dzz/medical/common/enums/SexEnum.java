package com.dzz.medical.common.enums;

import java.util.Objects;

/**
 * 性别枚举
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:01
 */
public enum  SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer code;

    private String name;

    SexEnum(Integer code, String name) {
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
        for (SexEnum status : SexEnum.values()) {
            if (Objects.equals(code, status.getCode())) {
                return status.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name) {
        for (SexEnum type : SexEnum.values()) {
            if (Objects.equals(name, type.getName())) {
                return type.getCode();
            }
        }
        return null;
    }
}
