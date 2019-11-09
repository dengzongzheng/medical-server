package com.dzz.medical.common.enums;

import java.util.Objects;

/**
 * 性别枚举
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:01
 */
public enum RoleEnum {

    /**
     * 性别枚举
     */
    M_ROLE("m_user", "前端用户"),
    MANAGER_ROLE("admin_user", "后端管理用户");

    private String code;

    private String name;

    RoleEnum(String code, String name) {
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
        for (RoleEnum status : RoleEnum.values()) {
            if (Objects.equals(code, status.getCode())) {
                return status.getName();
            }
        }
        return "";
    }

    public static String getCodeByName(String name) {
        for (RoleEnum type : RoleEnum.values()) {
            if (Objects.equals(name, type.getName())) {
                return type.getCode();
            }
        }
        return null;
    }
}
