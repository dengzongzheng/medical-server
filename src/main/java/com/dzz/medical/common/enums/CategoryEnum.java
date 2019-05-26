package com.dzz.medical.common.enums;

import java.util.Objects;

/**
 * 类别分类
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月24 10:09
 */
public enum  CategoryEnum {

    WORKNEWS(1, "工作动态"),
    NOTICE(2, "通知公告"),
    LEGAL(3, "法律法规"),
    INFORMATION(4, "信息");

    private Integer code;

    private String name;

    CategoryEnum(Integer code, String name) {
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
        for (CategoryEnum status : CategoryEnum.values()) {
            if (Objects.equals(code, status.getCode())) {
                return status.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name) {
        for (CategoryEnum type : CategoryEnum.values()) {
            if (Objects.equals(name, type.getName())) {
                return type.getCode();
            }
        }
        return null;
    }

}
