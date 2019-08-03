package com.dzz.medical.controller;

import com.dzz.medical.common.codec.UserAccountUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.WebsiteUserDetailBo;
import com.dzz.medical.domain.dto.LoginParamDto;
import com.dzz.medical.domain.dto.WebsiteLoginDto;
import com.dzz.medical.domain.tools.BeanConvertTools;
import com.dzz.medical.service.WebsiteUserService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台登录控制层
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:21
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class WebsiteLoginController {

    private WebsiteUserService websiteUserService;

    @Autowired
    public void setWebsiteUserService(WebsiteUserService websiteUserService) {
        this.websiteUserService = websiteUserService;
    }

    /**
     * 用户自主注册
     * @return 结果
     */
    @PostMapping("/websiteRegister")
    public ResponseDzz websiteRegister(@RequestBody WebsiteLoginDto loginDto) {

        log.info("接收到的数据为:{}", loginDto.toString());
        websiteUserService.saveWebsiteUser(BeanConvertTools.convertToWebsiteUser(loginDto));
        return ResponseDzz.ok();
    }



    /**
     * 用户登录
     * @param param 用户数据
     * @return 登录结果
     */
    @PostMapping("/websiteLogin")
    public ResponseDzz websiteLogin(@RequestBody LoginParamDto param) {

        if(Strings.isNullOrEmpty(param.getUserName()) || Strings.isNullOrEmpty(param.getPassword())) {
            return ResponseDzz.fail("用户名密码不能为空");
        }
        ResponseDzz<WebsiteUserDetailBo> userDetailResponseDzz = websiteUserService
                .findWebsiteUserByName(param.getUserName());
        if(userDetailResponseDzz.checkFail()||null==userDetailResponseDzz.getData()) {
            return ResponseDzz.fail("用户名或密码错误");
        }
        WebsiteUserDetailBo userDetail = userDetailResponseDzz.getData();
        if(!UserAccountUtil.validatePassword(param.getUserName(), param.getPassword(), userDetail.getPassword())) {
            return ResponseDzz.fail("用户名或密码错误");
        }
        return ResponseDzz.ok(param.getUserName());
    }

}
