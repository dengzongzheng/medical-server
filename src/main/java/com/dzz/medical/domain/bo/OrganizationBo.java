package com.dzz.medical.domain.bo;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 类别行业BO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:36
 */
@Data
public class OrganizationBo implements Serializable {

    private static final long serialVersionUID = -1319798726776199551L;

    /**
     * 类别code
     */
    private String organizationCode;

    /**
     * 类别名称
     */
    private String organizationName;


    /**
     * 行业列表
     */
    private List<IndustryBo> industries;
}
