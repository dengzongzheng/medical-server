package com.dzz.medical.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 资源服务配置
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月08 10:43
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("ALL")
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
        //配置授保护资源信息
        http.authorizeRequests()
                .antMatchers("/api/user/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
	    //资源服务配置
        resources.resourceId("info");
//		resources.accessDeniedHandler(getAccessDeniedHandler()).authenticationEntryPoint(getAuthenticationEntryPoint());
	}

	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
	    //异常处理
		return new SelfAccessDeniedHandlerImpl();
	}


	@Bean
	public AuthenticationEntryPoint getAuthenticationEntryPoint() {
	    //匿名访问处理
		return new AuthenticationEntryPointImpl();
	}
}
