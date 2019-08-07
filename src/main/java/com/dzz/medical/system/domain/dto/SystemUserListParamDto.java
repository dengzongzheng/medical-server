package com.dzz.medical.system.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统用户列表查询DTO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "系统用户列表查询入参")
public class SystemUserListParamDto implements Serializable {

    private static final long serialVersionUID = 4381798040022496457L;

    /**
     * 内容查询
     */
    @ApiModelProperty(value = "内容查询")
    private String content;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", dataType = "int", example = "1")
    private Integer status;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true, dataType = "int", example = "1")
    private Integer pageNo = 1;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", required = true, dataType = "int", example = "10")
    private Integer pageSize = 10;
}
