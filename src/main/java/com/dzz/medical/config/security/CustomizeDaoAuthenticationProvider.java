package com.dzz.medical.config.security;

import com.alibaba.fastjson.JSON;
import com.dzz.medical.common.enums.RoleEnum;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.service.SuperviseUserService;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 重写认证
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月11 10:13
 */
@Slf4j
public class CustomizeDaoAuthenticationProvider extends DaoAuthenticationProvider {

    private SuperviseUserService superviseUserService;

    public void setSuperviseUserService(SuperviseUserService superviseUserService) {
        this.superviseUserService = superviseUserService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        log.info("取到的参数为:{}", JSON.toJSONString(authentication.getDetails()));

        Map<String, String> param = (LinkedHashMap<String, String>) authentication.getDetails();
        String loginType = param.get("type");

        if(!loginType.equalsIgnoreCase(
                RoleEnum.M_ROLE.getCode()) || !loginType.equalsIgnoreCase(RoleEnum.MANAGER_ROLE.getCode())) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        //前台用户
        if(RoleEnum.M_ROLE.getCode().equals(loginType)) {

            ResponseDzz<SuperviseUserDetailBo> responseDzz = superviseUserService
                    .getSuperviseUserByName(param.get("username"), RoleEnum.M_ROLE.getCode());
            if(responseDzz.checkFail()) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"));
            }
        }

        if(RoleEnum.MANAGER_ROLE.getCode().equalsIgnoreCase(loginType)){
            ResponseDzz<SuperviseUserDetailBo> responseDzz = superviseUserService
                    .getSuperviseUserByName(param.get("username"), RoleEnum.MANAGER_ROLE.getCode());
            if(responseDzz.checkFail()) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"));
            }
        }

    }
}
