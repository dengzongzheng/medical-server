package com.dzz.medical.system.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "系统用户保存入参")
public class SystemUserSaveParam implements Serializable {

    private static final long serialVersionUID = 2699892017698962733L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 状态1:有效 0:无效
     */
    @ApiModelProperty(value = "状态1", notes = "1:有效 0:无效", dataType = "int")
    private Integer status = 1;

    /**
     * 所管理的组织机构信息
     * @see com.dzz.medical.common.enums.OrganizationEnum code
     */
    @NotEmpty(message = "所管理的组织机构信息不能为空")
    private List<Integer> organizations;
}
