package com.dzz.medical.util.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录日志列表查询参数
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 18:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginLogListParamDto implements Serializable {

    private static final long serialVersionUID = 2597077608909204387L;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 当前页
     */
    private Integer pageNo = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;
}
