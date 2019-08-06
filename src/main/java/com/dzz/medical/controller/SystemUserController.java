package com.dzz.medical.controller;

import com.dzz.medical.common.bean.BeanTools;
import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.config.exception.BusinessException;
import com.dzz.medical.domain.bo.SystemUserBo;
import com.dzz.medical.domain.dto.SystemUserListParamDto;
import com.dzz.medical.domain.dto.SystemUserLoginParamDto;
import com.dzz.medical.domain.dto.SystemUserSaveParamDto;
import com.dzz.medical.domain.model.SystemUser;
import com.dzz.medical.domain.model.UserLoginLog;
import com.dzz.medical.service.SystemUserService;
import com.dzz.medical.service.UserLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户Controller
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月03 20:47
 */
@RestController
@RequestMapping("/api/system")
@Slf4j
public class SystemUserController extends BaseController{

    private SystemUserService systemUserService;

    private UserLoginLogService loginLogService;

    @Autowired
    public void setSystemUserService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Autowired
    public void setLoginLogService(UserLoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * 用户登录
     *
     * @param param 用户数据
     * @param bindingResult 数据校验结果
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseDzz login(@RequestBody @Validated SystemUserLoginParamDto param, BindingResult bindingResult) {

        bindResultHandler(bindingResult);
        ResponseDzz<SystemUser> responseDzz = systemUserService.getUserByName(param.getUserName());
        if(responseDzz.checkFail() || null == responseDzz.getData()) {
            throw new BusinessException("用户名或密码错误");
        }
        SystemUser user = responseDzz.getData();
        if(!user.getPassword().equals(param.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        loginLogService.save(UserLoginLog.builder().userName(user.getUserName()).userNo(user.getUserNo())
                .loginTime(System.currentTimeMillis()).build());
        return ResponseDzz.ok(param.getUserName());
    }

    /**
     * 系统用户添加
     * @return 结果
     */
    @PostMapping("/saveUser")
    public ResponseDzz saveUser(@RequestBody @Validated SystemUserSaveParamDto param,BindingResult bindingResult) {

        log.info("接收到的数据为:{}", param.toString());
        bindResultHandler(bindingResult);
        return systemUserService.saveUser(BeanTools.convertToSystemUser(param));
    }

    /**
     * 列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listUsers")
    public ResponseDzz<PageUtil> listUsers(SystemUserListParamDto param){
        log.info("接收到的数据为:{}", param.toString());

        return systemUserService.listSystemUser(param);
    }


    /**
     * 详情
     * @param userNo 编号
     * @return 结果
     */
    @GetMapping("/detailUser")
    public ResponseDzz<SystemUserBo> detailUser(@RequestParam("userNo") String userNo){

        return systemUserService.getUserByNo(userNo);
    }

}
