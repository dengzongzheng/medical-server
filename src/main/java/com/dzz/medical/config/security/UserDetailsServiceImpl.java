package com.dzz.medical.config.security;

import com.dzz.medical.common.enums.RoleEnum;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.service.SuperviseUserService;
import com.dzz.medical.system.domain.model.SystemUser;
import com.dzz.medical.system.service.SystemUserService;
import java.util.Arrays;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月08 11:28
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements CustomizeUserDetailsService {


    private SuperviseUserService superviseUserService;

    private SystemUserService systemUserService;

    @Autowired
    public void setSuperviseUserService(SuperviseUserService superviseUserService) {
        this.superviseUserService = superviseUserService;
    }

    @Autowired
    public void setSystemUserService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUser:{}", username);
        ResponseDzz<SuperviseUserDetailBo> responseDzz = superviseUserService.getSuperviseUserByName(username);
        if(responseDzz.checkFail() || Objects.isNull(responseDzz.getData())) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        SuperviseUserDetailBo userDetailBo = responseDzz.getData();
        return User.builder().username(userDetailBo.getUserName()).password(userDetailBo.getPassword())
                .roles(userDetailBo.getRole()).authorities(Arrays.asList(new Authority("test"), new Authority("abc")))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username, String loginType) throws UsernameNotFoundException {

        log.info("访问我了.....{},{}",username,loginType);
        //前台用户
        if(RoleEnum.M_ROLE.getCode().equals(loginType)) {

            ResponseDzz<SuperviseUserDetailBo> responseDzz = superviseUserService.getSuperviseUserByName(username);
            if(responseDzz.checkFail() || Objects.isNull(responseDzz.getData())) {
                throw new BadCredentialsException("用户名或密码错误");
            }
            SuperviseUserDetailBo userDetailBo = responseDzz.getData();
            return User.builder().username(userDetailBo.getUserName()).password(userDetailBo.getPassword())
                    .roles(userDetailBo.getRole()).authorities(Arrays.asList(new Authority("test"), new Authority("abc")))
                    .build();
        }

        //后台用户
        if(RoleEnum.MANAGER_ROLE.getCode().equalsIgnoreCase(loginType)){
            ResponseDzz<SystemUser> responseDzz = systemUserService.getUserByName(username);
            if(responseDzz.checkFail() || Objects.isNull(responseDzz.getData())) {
                throw new BadCredentialsException("用户名或密码错误");
            }
            SystemUser systemUser = responseDzz.getData();
            return User.builder().username(systemUser.getUserName()).password(systemUser.getPassword())
                    .roles(RoleEnum.MANAGER_ROLE.getCode())
                    .authorities(Arrays.asList(new Authority("test"), new Authority("abc"))).build();
        }
        return null;
    }
}
