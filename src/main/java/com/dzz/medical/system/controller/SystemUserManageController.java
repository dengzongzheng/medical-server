package com.dzz.medical.system.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.system.domain.bo.SystemUserBo;
import com.dzz.medical.system.domain.dto.SystemUserListParam;
import com.dzz.medical.system.domain.dto.SystemUserSaveParam;
import com.dzz.medical.system.domain.dto.SystemUserUpdateParam;
import com.dzz.medical.system.domain.model.SystemUser;
import com.dzz.medical.system.service.SystemUserService;
import com.dzz.medical.util.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监督用户Controller
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月18 15:53
 */
@RestController
@RequestMapping("/api/system")
@Api(value = "监督用户管理", tags = "2、监督用户管理")
@Slf4j
public class SystemUserManageController extends BaseController {


    private SystemUserService systemUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setSystemUserService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @PostMapping("/addUser")
    public ResponseDzz addUser(@RequestBody @Validated SystemUserSaveParam param, BindingResult result) {

        log.info("接收到的参数:{}", param);
        bindResultHandler(result);
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(param, systemUser);
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        return systemUserService.saveUser(systemUser);
    }


    @PostMapping("/updateUser")
    public ResponseDzz updateUser(@RequestBody @Validated SystemUserUpdateParam param, BindingResult result) {

        log.info("接收到的参数:{}", param);
        bindResultHandler(result);
        return systemUserService.updateUser(param);
    }

    /**
     * 列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listUsers")
    public ResponseDzz<PageUtil> listUsers(SystemUserListParam param){

        log.info("接收到的参数:{}", param);
        return systemUserService.listSystemUser(param);
    }


    /**
     * 详情
     * @param userNo 编号
     * @return 结果
     */
    @GetMapping("/detail")
    public ResponseDzz<SystemUserBo> detail(@RequestParam("userNo") String userNo){

        return systemUserService.getUserByNo(userNo);
    }
}
