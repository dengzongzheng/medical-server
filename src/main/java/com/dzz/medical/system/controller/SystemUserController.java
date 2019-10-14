//package com.dzz.medical.system.controller;
//
//import com.dzz.medical.common.bean.BeanTools;
//import com.dzz.medical.common.page.PageUtil;
//import com.dzz.medical.common.response.ResponseDzz;
//import com.dzz.medical.config.exception.BusinessException;
//import com.dzz.medical.util.controller.BaseController;
//import com.dzz.medical.system.domain.bo.SystemUserBo;
//import com.dzz.medical.system.domain.dto.SystemUserListParam;
//import com.dzz.medical.system.domain.dto.SystemUserLoginParam;
//import com.dzz.medical.system.domain.dto.SystemUserSaveParam;
//import com.dzz.medical.system.domain.model.Role;
//import com.dzz.medical.system.domain.model.SystemUser;
//import com.dzz.medical.supervise.domain.model.UserLoginLog;
//import com.dzz.medical.system.service.SystemUserService;
//import com.dzz.medical.util.service.UserLoginLogService;
//import com.google.common.collect.Lists;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import javax.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 系统用户Controller
// *
// * @author dzz
// * @version 1.0.0
// * @since 2019年08月03 20:47
// */
//@RestController
//@RequestMapping("/api/system")
//@Api(value = "系统用户管理", tags = "系统用户管理")
//@Slf4j
//public class SystemUserController extends BaseController {
//
//
//    private SystemUserService systemUserService;
//
//    private UserLoginLogService loginLogService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public void setSystemUserService(SystemUserService systemUserService) {
//        this.systemUserService = systemUserService;
//    }
//
//    @Autowired
//    public void setLoginLogService(UserLoginLogService loginLogService) {
//        this.loginLogService = loginLogService;
//    }
//
//    /**
//     * 用户登录
//     *
//     * @param param 用户数据
//     * @param bindingResult 数据校验结果
//     * @return 登录结果
//     */
//    @PostMapping("/login")
//    @ApiOperation(value = "系统用户登录", notes = "备注说明")
//    public ResponseDzz login(@RequestBody @Validated SystemUserLoginParam param, BindingResult bindingResult) {
//
//        bindResultHandler(bindingResult);
//        ResponseDzz<SystemUser> responseDzz = systemUserService.getUserByName(param.getUserName());
//        if(responseDzz.checkFail() || null == responseDzz.getData()) {
//            throw new BusinessException("用户名或密码错误");
//        }
//        SystemUser user = responseDzz.getData();
//        log.info("取一下密码:{}",passwordEncoder.encode(param.getPassword()));
//        try {
//            authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(param.getUserName(), param.getPassword()));
//            loginLogService.save(UserLoginLog.builder().userName(user.getUserName()).userNo(user.getUserNo())
//                    .loginTime(System.currentTimeMillis()).build());
//            return ResponseDzz.ok(jwtTokenProvider.createToken(user.getUserName(), Lists.newArrayList(new Role("ADMIN"))));
//        } catch (AuthenticationException e) {
//            throw new BusinessException("用户名或密码错误");
//        }
//    }
//
//    /**
//     * 用户刷新
//     * @param request request
//     * @return 用户刷新结果
//     */
//    @GetMapping("/refresh")
//    @ApiOperation(value = "用户刷新", notes = "备注说明")
//    public ResponseDzz refresh(HttpServletRequest request) {
//
//        String userName =request.getRemoteUser();
//        ResponseDzz<SystemUser> responseDzz = systemUserService.getUserByName(userName);
//        if(responseDzz.checkFail() || null == responseDzz.getData()) {
//            throw new BusinessException("用户不存在");
//        }
//        return ResponseDzz.ok(jwtTokenProvider.createToken(userName, responseDzz.getData().getRoles()));
//    }
//
//    /**
//     * 系统用户添加
//     * @return 结果
//     */
//    @ApiOperation(value = "系统用户添加", notes = "备注说明")
//    @PostMapping("/saveUser")
//    public ResponseDzz saveUser(@RequestBody @Validated SystemUserSaveParam param,BindingResult bindingResult) {
//
//        log.info("接收到的数据为:{}", param.toString());
//        bindResultHandler(bindingResult);
//        param.setPassword(passwordEncoder.encode(param.getPassword()));
//        return systemUserService.saveUser(BeanTools.convertToSystemUser(param));
//    }
//
//    /**
//     * 列表查询
//     * @param param 查询条件
//     * @return 结果
//     */
//    @GetMapping("/listUsers")
//    @ApiOperation(value = "列表查询", notes = "备注说明")
//    public ResponseDzz<PageUtil> listUsers(SystemUserListParam param){
//        log.info("接收到的数据为:{}", param.toString());
//
//        return systemUserService.listSystemUser(param);
//    }
//
//
//    /**
//     * 详情
//     * @param userNo 编号
//     * @return 结果
//     */
//    @GetMapping("/detailUser")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @ApiOperation(value = "获取系统用户详细信息", notes = "备注说明")
//    @ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "String", paramType = "query")
//    public ResponseDzz<SystemUserBo> detailUser(@RequestParam("userNo") String userNo){
//
//        return systemUserService.getUserByNo(userNo);
//    }
//
//}
