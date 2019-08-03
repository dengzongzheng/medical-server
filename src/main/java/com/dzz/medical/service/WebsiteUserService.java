package com.dzz.medical.service;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.WebsiteUserDetailBo;
import com.dzz.medical.domain.dto.ListWebsiteUserParamDto;
import com.dzz.medical.domain.model.WebsiteUser;

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
    ResponseDzz<PageUtil> listWebsiteUser(ListWebsiteUserParamDto param);



    /**
     * 根据用户名查用户信息
     * @param userName 用户名
     * @return 结果
     */
    ResponseDzz<WebsiteUserDetailBo> findWebsiteUserByName(String userName);


    /**
     * 根据用户名取用户信息
     * @param userNo 用户编号
     * @return 用户信息
     */
    ResponseDzz<WebsiteUserDetailBo> getUserByNo(String userNo);
}