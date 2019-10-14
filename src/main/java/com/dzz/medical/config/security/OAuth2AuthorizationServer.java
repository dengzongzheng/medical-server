package com.dzz.medical.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * OAuth授权服务配置
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月08 09:40
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
@SuppressWarnings("ALL")
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * 注入AuthenticationManager ，密码模式用到
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //单机模式客户端,放于内存中便可
        clients.inMemory().withClient("client_1")
                .resourceIds("info")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret(passwordEncoder.encode("123456"));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //授权配置，添加认证管理器及token持久化方式
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(redisTokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //授权访问控制
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }


    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

}
