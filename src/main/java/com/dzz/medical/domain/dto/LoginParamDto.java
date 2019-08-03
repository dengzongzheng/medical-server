package com.dzz.medical.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月18 17:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginParamDto implements Serializable {

    private static final long serialVersionUID = -2978260295967439819L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
