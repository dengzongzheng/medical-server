package com.dzz.medical.system.service;


import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.system.domain.bo.SystemUserBo;
import com.dzz.medical.system.domain.dto.SystemUserListParam;
import com.dzz.medical.system.domain.model.SystemUser;

/**
 * 用户服务接口
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月29 15:07
 */
public interface SystemUserService {

    String COLLECTION_NAME = "user";

    /**
     * 根据用户名取用户信息
     * @param userName 用户名称
     * @return 用户信息
     */
    ResponseDzz<SystemUser> getUserByName(String userName);



    /**
     * 保存用户信息
     * @param user 用户信息
     * @return 结果
     */
    ResponseDzz saveUser(SystemUser user);


    /**
     * 系统用户列表信息查询
     * @param param 查询条件
     * @return 结果
     */
    ResponseDzz<PageUtil> listSystemUser(SystemUserListParam param);


    /**
     * 根据用户编号取用户信息
     * @param userNo 用户编号
     * @return 用户信息
     */
    ResponseDzz<SystemUserBo> getUserByNo(String userNo);
}
