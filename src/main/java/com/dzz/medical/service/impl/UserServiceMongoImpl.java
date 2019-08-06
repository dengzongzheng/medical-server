package com.dzz.medical.service.impl;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.domain.model.SystemUser;
import com.dzz.medical.service.SuperviseUserService;
import com.dzz.medical.service.SystemUserService;
import com.dzz.medical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月06 15:36
 */
@Service
public class UserServiceMongoImpl implements UserService {

    private SystemUserService systemUserService;

    private SuperviseUserService superviseUserService;

    @Autowired
    public void setSystemUserService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Autowired
    public void setSuperviseUserService(SuperviseUserService superviseUserService) {
        this.superviseUserService = superviseUserService;
    }

    @Override
    public ResponseDzz<SystemUser> getSystemUserByName(String userName) {
        return systemUserService.getUserByName(userName);
    }

    @Override
    public ResponseDzz<SuperviseUserDetailBo> getSuperviseUserByName(String userName) {
        return superviseUserService.getSuperviseUserByName(userName);
    }

}
