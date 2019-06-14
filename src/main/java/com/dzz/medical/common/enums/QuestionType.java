package com.dzz.medical.common.enums;

import java.util.Objects;

/**
 * 问题类型
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:25
 */
public enum QuestionType {

    SELECT(1, "选择题"),
    FILL_IN_BLANK (2, "填空题");

    private Integer code;

    private String name;

    QuestionType(Integer code, String name) {
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
        for (QuestionType status : QuestionType.values()) {
            if (Objects.equals(code, status.getCode())) {
                return status.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name) {
        for (QuestionType type : QuestionType.values()) {
            if (Objects.equals(name, type.getName())) {
                return type.getCode();
            }
        }
        return null;
    }

}
