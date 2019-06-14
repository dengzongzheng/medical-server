package com.dzz.medical.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 用户
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:25
 */
@Data
@Document(collection = "website_user")
public class WebsiteUser implements Serializable {

    private static final long serialVersionUID = -5326521964675727750L;

    /**
     * 用户名
     */
    @Field("user_name")
    @Indexed(unique = true)
    private String userName;


    /**
     * 密码
     */
    @Field("password")
    private String password;


    /**
     * 用户微信Id
     */
    @Field("open_id")
    private String openId;

    /**
     * 用户微信昵称
     */
    @Field("nick_name")
    private String nickName;

    /**
     * 用户微信头像
     */
    @Field("avatar")
    private String avatar;


    /**
     * 分类行业信息
     */
    @Field("organizations")
    private List<Organization> organizations;


    /**
     * 企业名称
     */
    @Field("company_name")
    private String companyName;


    /**
     * 负责人
     */
    @Field("principal")
    private String principal;

    /**
     * 身份证号
     */
    @Field("cert_no")
    private String certNo;

    /**
     * 性别
     * @see com.dzz.medical.common.enums.SexEnum code
     */
    @Field("sex")
    private Integer sex;

    /**
     * 性别名称
     * @see com.dzz.medical.common.enums.SexEnum name
     */
    @Field("sex_name")
    private String sexName;

    /**
     * 民族
     */
    @Field("ethnic")
    private String ethnic;

    /**
     * 地址
     */
    @Field("address")
    private String address;

    /**
     * 电话
     */
    @Field("mobile")
    private String mobile;

    /**
     * 管理人员姓名
     */
    @Field("manager_name")
    private String managerName;


    /**
     * 管理人员电话
     */
    @Field("manager_mobile")
    private String managerMobile;

    /**
     * 创建时间
     */
    @Field("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Field("update_time")
    private Date updateTime;

}

