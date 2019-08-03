package com.dzz.medical.domain.bo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 行业
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndustryBo implements Serializable {

    private static final long serialVersionUID = 4287492969428231859L;

    /**
     * 行业代码
     */
    private String code;

    /**
     * 行业名称
     */
    private String name;

}
