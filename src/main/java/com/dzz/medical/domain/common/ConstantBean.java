package com.dzz.medical.domain.common;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 枚举类实体
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年04月19 下午3:03
 */
@Data
@Accessors(chain = true)
public class ConstantBean implements Serializable {

    private static final long serialVersionUID = 7807213824619814045L;

    /**
     * 枚举编码
     */
    private Integer code;

    /**
     * 枚举编码含义
     */
    private String name;
}
