package com.dzz.medical.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 文单、消息DTO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月26 21:40
 */
@Data
public class ListNewsParamDto implements Serializable {

    private static final long serialVersionUID = -9125734233165605462L;

    /**
     * 内容查询
     */
    private String content;

    /**
     * 分类code
     * @see com.dzz.medical.common.enums.CategoryEnum code
     */
    private Integer categoryCode;


    private Integer status;


    private Integer pageNo;


    private Integer pageSize;
}
