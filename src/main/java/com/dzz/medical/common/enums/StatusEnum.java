package com.dzz.medical.common.enums;

import java.util.Objects;

/**
 * 类别分类
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月24 10:09
 */
public enum StatusEnum {

    ZC(1, "正常"),
    XX(2, "下线"),
    PZING(0, "配置中");

    private Integer code;

    private String name;

    StatusEnum(Integer code, String name) {
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
        for (StatusEnum status : StatusEnum.values()) {
            if (Objects.equals(code, status.getCode())) {
                return status.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name) {
        for (StatusEnum type : StatusEnum.values()) {
            if (Objects.equals(name, type.getName())) {
                return type.getCode();
            }
        }
        return null;
    }

}
