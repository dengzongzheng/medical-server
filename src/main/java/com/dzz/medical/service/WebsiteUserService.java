package com.dzz.medical.service;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.WebsiteUserDetail;
import com.dzz.medical.domain.dto.ListWebsiteUserParamDto;
import com.dzz.medical.domain.model.WebsiteUser;
import com.dzz.medical.domain.vo.WebsiteUserListVo;

/**
 * 注册用户接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:06
 */
public interface WebsiteUserService {


    /**
     * 保存注册用户信息
     * @param websiteUser 注册用户信息
     * @return 结果
     */
    ResponseDzz saveWebsiteUser(WebsiteUser websiteUser);


    /**
     * 行业类别列表信息查询
     * @param param 查询条件
     * @return 结果
     */
    ResponseDzz<WebsiteUserListVo> listWebsiteUser(ListWebsiteUserParamDto param);



    /**
     * 根据用户名查用户信息
     * @param userName 用户名
     * @return 结果
     */
    ResponseDzz<WebsiteUserDetail> findWebsiteUserByName(String userName);
}