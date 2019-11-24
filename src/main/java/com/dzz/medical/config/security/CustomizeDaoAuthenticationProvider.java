package com.dzz.medical.config.security;

import com.alibaba.fastjson.JSON;
import com.dzz.medical.common.enums.RoleEnum;
import com.dzz.medical.supervise.service.SuperviseUserService;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * 重写认证
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月11 10:13
 */
@Slf4j
public class CustomizeDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

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
        log.info("登录类型为:{}", loginType);
        if(!loginType.equalsIgnoreCase(
                RoleEnum.M_ROLE.getCode()) && !loginType.equalsIgnoreCase(RoleEnum.MANAGER_ROLE.getCode())) {
            log.info("登录类型错误，必须为:{}或{}", RoleEnum.M_ROLE.getName(), RoleEnum.MANAGER_ROLE.getName());
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

    }

    /**
     * The plaintext password used to perform
     * PasswordEncoder#matches(CharSequence, String)}  on when the user is
     * not found to avoid SEC-2056.
     */
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    // ~ Instance fields
    // ================================================================================================

    private PasswordEncoder passwordEncoder;

    /**
     * The password used to perform
     * {@link PasswordEncoder#matches(CharSequence, String)} on when the user is
     * not found to avoid SEC-2056. This is necessary, because some
     * {@link PasswordEncoder} implementations will short circuit if the password is not
     * in a valid format.
     */
    private volatile String userNotFoundEncodedPassword;

    private CustomizeUserDetailsService userDetailsService;

    private UserDetailsPasswordService userDetailsPasswordService;

    public CustomizeDaoAuthenticationProvider() {
        setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    // ~ Methods
    // ========================================================================================================



    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    protected final UserDetails retrieveUser(String username,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        prepareTimingAttackProtection();
        try {
            Map<String, String> param = (LinkedHashMap<String, String>) authentication.getDetails();
            String loginType = param.get("type");
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username,loginType);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        }
        catch (UsernameNotFoundException ex) {
            mitigateAgainstTimingAttack(authentication);
            throw ex;
        }
        catch (InternalAuthenticationServiceException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal,
            Authentication authentication, UserDetails user) {
        boolean upgradeEncoding = this.userDetailsPasswordService != null
                && this.passwordEncoder.upgradeEncoding(user.getPassword());
        if (upgradeEncoding) {
            String presentedPassword = authentication.getCredentials().toString();
            String newPassword = this.passwordEncoder.encode(presentedPassword);
            user = this.userDetailsPasswordService.updatePassword(user, newPassword);
        }
        return super.createSuccessAuthentication(principal, authentication, user);
    }

    private void prepareTimingAttackProtection() {
        if (this.userNotFoundEncodedPassword == null) {
            this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
        }
    }

    private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
            this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
        }
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords. If
     * not set, the password will be compared using {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
     *
     * @param passwordEncoder must be an instance of one of the {@code PasswordEncoder}
     * types.
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setUserDetailsService(CustomizeUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected CustomizeUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsPasswordService(
            UserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailsPasswordService = userDetailsPasswordService;
    }


}
