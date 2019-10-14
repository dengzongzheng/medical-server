package com.dzz.medical.system.controller;

import com.dzz.medical.common.codec.UserAccountUtil;
import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.config.exception.BusinessException;
import com.dzz.medical.util.controller.BaseController;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.domain.dto.SuperviseUserListParam;
import com.dzz.medical.supervise.domain.dto.SuperviseUserLoginParam;
import com.dzz.medical.supervise.domain.dto.SuperviseUserRegisterParam;
import com.dzz.medical.common.bean.BeanConvertTools;
import com.dzz.medical.supervise.service.SuperviseUserService;
import com.dzz.medical.supervise.service.impl.SuperviseUserServiceMongoImpl;
import io.swagger.annotations.Api;
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
 * 监督用户Controller
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月18 15:53
 */
@RestController
@RequestMapping("/api/supervise")
@Api(value = "监督用户管理", tags = "2、监督用户管理")
@Slf4j
public class SuperviseUserController extends BaseController {

    private SuperviseUserService superviseUserService;

    @Autowired
    public void setWebsiteUserService(SuperviseUserServiceMongoImpl superviseUserService) {
        this.superviseUserService = superviseUserService;
    }


    /**
     * 用户自主注册
     * @return 结果
     */
    @PostMapping("/register")
    public ResponseDzz register(@RequestBody SuperviseUserRegisterParam param) {

        log.info("接收到的数据为:{}", param.toString());
        return superviseUserService.saveSuperviseUser(BeanConvertTools.convertToWebsiteUser(param));
    }



    /**
     * 用户登录
     * @param param 用户数据
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseDzz login(@RequestBody @Validated SuperviseUserLoginParam param, BindingResult bindingResult) {

        bindResultHandler(bindingResult);
        ResponseDzz<SuperviseUserDetailBo> userDetailResponseDzz = superviseUserService
                .getSuperviseUserByName(param.getUserName());
        if(userDetailResponseDzz.checkFail()||null==userDetailResponseDzz.getData()) {
            throw new BusinessException("用户名或密码错误");
        }
        SuperviseUserDetailBo userDetail = userDetailResponseDzz.getData();
        if(!UserAccountUtil.validatePassword(param.getUserName(), param.getPassword(), userDetail.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        return ResponseDzz.ok(param.getUserName());
    }

    /**
     * 列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listUsers")
    public ResponseDzz<PageUtil> listUsers(SuperviseUserListParam param){

        return superviseUserService.listSuperviseUser(param);
    }


    /**
     * 详情
     * @param userNo 编号
     * @return 结果
     */
    @GetMapping("/detail")
    public ResponseDzz<SuperviseUserDetailBo> detail(@RequestParam("userNo") String userNo){

        return superviseUserService.getSuperviseUserByNo(userNo);
    }
}
