package com.dzz.medical.supervise.domain.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 用户登录日志
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 18:39
 */
@Data
@Document(collection = "user_login_log")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = -4783757310204115142L;

    /**
     * 用户编号
     */
    @Field("user_no")
    @Indexed(unique = false)
    private String userNo;

    /**
     * 用户名
     */
    @Field("user_name")
    private String userName;

    /**
     * 登录时间
     */
    @Field("login_time")
    private Long loginTime;
}
