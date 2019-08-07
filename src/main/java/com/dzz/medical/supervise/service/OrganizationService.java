package com.dzz.medical.supervise.service;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.model.Organization;

/**
 * 机构类别接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:43
 */
public interface OrganizationService {

    /**
     * 保存行业类别信息
     * @param organization 行业别信息
     * @return 结果
     */
    ResponseDzz saveOrganization(Organization organization);


    /**
     * 行业类别列表信息查询
     * @return 结果
     */
    ResponseDzz listOrganization();
}