package com.dzz.medical.util.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.util.domain.dto.UserLoginLogListParamDto;
import com.dzz.medical.util.service.UserLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录日志Controller
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 18:57
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserLoginLogController {

    private UserLoginLogService loginLogService;

    @Autowired
    public void setLoginLogService(UserLoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * 列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listUserLoginLog")
    public ResponseDzz<PageUtil> listUserLoginLog(UserLoginLogListParamDto param){
        log.info("接收到的数据为:{}", param.toString());

        return loginLogService.listUserLoginLog(param);
    }
}
