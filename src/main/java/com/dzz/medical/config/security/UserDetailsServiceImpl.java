package com.dzz.medical.config.security;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.service.SuperviseUserService;
import java.util.Arrays;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class UserDetailsServiceImpl implements UserDetailsService {


    private SuperviseUserService superviseUserService;

    @Autowired
    public void setSuperviseUserService(SuperviseUserService superviseUserService) {
        this.superviseUserService = superviseUserService;
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
}
