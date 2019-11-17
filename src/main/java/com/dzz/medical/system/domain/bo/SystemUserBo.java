package com.dzz.medical.system.domain.bo;

import com.dzz.medical.common.date.DateConvertTools;
import com.dzz.medical.common.enums.OrganizationEnum;
import com.dzz.medical.common.enums.StatusEnum;
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
public class SystemUserBo {

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
     * @see StatusEnum code
     */
    @Field("status")
    private Integer status;

    /**
     * 状态名称
     * @see StatusEnum name
     */
    private String statusName;

    /**
     * 所管理的组织机构信息
     * @see com.dzz.medical.common.enums.OrganizationEnum code
     */
    @Field("organizations")
    private List<Integer> organizations;

    /**
     * 所管理的组织机构信息
     * @see com.dzz.medical.common.enums.OrganizationEnum name
     */
    private String organizationsString;


    /**
     * 创建时间
     */
    @Field("create_date")
    private Long createDate;

    /**
     * 创建时间字符类型
     */
    private String createDateString;

    /**
     * 修改时间
     */
    @Field("update_date")
    private Long updateDate;

    /**
     * 修改时间字符型
     */
    private String updateDateString;

    /**
     * 最近登录时间
     */
    private String recentLoginTime;


    /**
     * 重写statusName get方法用于转义
     * @return 状态名称
     */
    public String getStatusName() {

        return StatusEnum.getNameByCode(this.status);
    }

    public String getRecentLoginTime() {

        return DateConvertTools.formatDate(updateDate);
    }

    public String getCreateDateString() {

        return DateConvertTools.formatDate(createDate);
    }

    public String getUpdateDateString() {
        return DateConvertTools.formatDate(updateDate);
    }

    public String getOrganizationsString() {

        StringBuilder temp = new StringBuilder();
        for(Integer code : organizations) {
            temp.append(OrganizationEnum.getNameByCode(code));
        }
        return temp.toString();
    }
}
