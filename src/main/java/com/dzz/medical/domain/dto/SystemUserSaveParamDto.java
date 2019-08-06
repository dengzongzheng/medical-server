package com.dzz.medical.domain.dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加系统用户DTO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 09:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserSaveParamDto implements Serializable {

    private static final long serialVersionUID = 2699892017698962733L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 状态1:有效 0:无效
     */
    private Integer status = 1;

    /**
     * 所管理的组织机构信息
     * @see com.dzz.medical.common.enums.OrganizationEnum code
     */
    @NotEmpty(message = "所管理的组织机构信息不能为空")
    private List<Integer> organizations;
}
