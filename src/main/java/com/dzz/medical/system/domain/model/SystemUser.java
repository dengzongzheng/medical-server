package com.dzz.medical.system.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 用户表
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月28 20:51
 */
@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUser {

    /**
     * 用户编号
     */
    @Field("user_no")
    @Indexed(unique = true)
    private String userNo;

    /**
     * 用户名
     */
    @Indexed(unique = true)
    @Field("user_name")
    private String userName;

    /**
     * 密码
     */
    @Field("password")
    private String password;

    /**
     * 状态1:有效 0:无效
     */
    @Field("status")
    private Integer status;

    /**
     * 所管理的组织机构信息
     * @see com.dzz.medical.common.enums.OrganizationEnum code
     */
    @Field("organizations")
    private List<Integer> organizations;


    /**
     * 用户角色
     */
    @Field("roles")
    private List<Role> roles;

    /**
     * 创建时间
     */
    @Field("create_date")
    private Long createDate;

    /**
     * 修改时间
     */
    @Field("update_date")
    private Long updateDate;
}
