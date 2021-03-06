package com.dzz.medical.supervise.service;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.domain.dto.SuperviseUserListParam;
import com.dzz.medical.supervise.domain.model.SuperviseUser;

/**
 * 注册用户接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:06
 */
public interface SuperviseUserService {

    String COLLECTION_NAME = "supervise_user";

    /**
     * 保存注册用户信息
     * @param websiteUser 注册用户信息
     * @return 结果
     */
    ResponseDzz saveSuperviseUser(SuperviseUser websiteUser);


    /**
     * 行业类别列表信息查询
     * @param param 查询条件
     * @return 结果
     */
    ResponseDzz<PageUtil> listSuperviseUser(SuperviseUserListParam param);



    /**
     * 根据用户名查用户信息
     * @param userName 用户名
     * @return 结果
     */
    ResponseDzz<SuperviseUserDetailBo> getSuperviseUserByName(String userName);


    /**
     * 根据用户名查用户信息
     * @param userName 用户名
     * @param role 用户角色
     * @return 结果
     */
    ResponseDzz<SuperviseUserDetailBo> getSuperviseUserByName(String userName,String role);

    /**
     * 根据用户名取用户信息
     * @param userNo 用户编号
     * @return 用户信息
     */
    ResponseDzz<SuperviseUserDetailBo> getSuperviseUserByNo(String userNo);
}