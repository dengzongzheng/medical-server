package com.dzz.medical.supervise.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户列表查询DTO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuperviseUserListParamDto implements Serializable {

    private static final long serialVersionUID = 4381798040022496457L;

    /**
     * 内容查询
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 当前页
     */
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
