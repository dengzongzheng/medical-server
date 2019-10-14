package com.dzz.medical.supervise.controller;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.dto.SuperviseUserRegisterParam;
import com.dzz.medical.supervise.domain.model.SuperviseUser;
import com.dzz.medical.supervise.service.SuperviseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户自律提交
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月18 16:13
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class SuperviseUserController {

    private SuperviseUserService superviseUserService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    public void setUserSuperviseService(SuperviseUserService superviseUserService) {
        this.superviseUserService = superviseUserService;
    }

    /**
     * 诚信自律用户注册
     * @param param 查询条件
     * @return 结果
     */
    @PostMapping("/register")
    public ResponseDzz register(@RequestBody SuperviseUserRegisterParam param){

        log.info("接收到的数据为:{}", param.toString());
        SuperviseUser websiteUser = new SuperviseUser();
        BeanUtils.copyProperties(param, websiteUser);
        return superviseUserService.saveSuperviseUser(websiteUser);
    }

    /**
     * 用户退出
     * @param token token
     */
    @GetMapping("/logout")
    public ResponseDzz logout(@RequestParam("access_token") String token) {

        consumerTokenServices.revokeToken(token);
        return ResponseDzz.ok();
    }

}
