package com.dzz.medical.config.security;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限实体类封装
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月10 08:24
 */
public class Authority implements GrantedAuthority, Serializable {


    private static final long serialVersionUID = 3713522414096420851L;

    public Authority(String authority) {
        this.authority = authority;
    }

    /**
     * 权限
     */
    private String authority;

    @Override
    public String getAuthority() {

        return authority;
    }
}
