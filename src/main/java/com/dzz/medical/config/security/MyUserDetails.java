package com.dzz.medical.config.security;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.model.Role;
import com.dzz.medical.domain.model.SystemUser;
import com.dzz.medical.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月28 20:51
 */
@Service
public class MyUserDetails implements UserDetailsService {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResponseDzz<SystemUser> responseDzz = userService.getSystemUserByName(username);

        if(responseDzz.checkFail() || null == responseDzz.getData()) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(responseDzz.getData().getPassword())
                .authorities(Lists.newArrayList(new Role("ROLE_ADMIN")))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
