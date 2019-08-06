package com.dzz.medical.service;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.domain.model.SystemUser;

/**
 * 用户接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月06 15:34
 */
public interface UserService {

    /**
     * 根据用户名取系统用户信息
     * @param userName 用户名称
     * @return 用户信息
     */
    ResponseDzz<SystemUser> getSystemUserByName(String userName);


    /**
     * 根据用户名查监督用户信息
     * @param userName 用户名
     * @return 结果
     */
    ResponseDzz<SuperviseUserDetailBo> getSuperviseUserByName(String userName);

}
