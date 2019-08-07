package com.dzz.medical.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月06 15:48
 */
@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 578299171702894695L;

    /**
     * 角色信息
     */
    @Field("role")
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
