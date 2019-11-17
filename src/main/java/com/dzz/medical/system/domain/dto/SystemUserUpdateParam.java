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
public class SystemUserUpdateParam implements Serializable {

    private static final long serialVersionUID = 2699892017698962733L;

    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID")
    @ApiModelProperty(value = "用户ID", required = true)
    private String userNo;

    /**
     * 密码
     */
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
