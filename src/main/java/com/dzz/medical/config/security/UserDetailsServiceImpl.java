package com.dzz.medical.config.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月08 11:28
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return User.builder().username("dzz1").password(passwordEncoder.encode("123456"))
                .authorities(Arrays.asList(new Authority("test"),new Authority("abc"))).build();
    }
}
