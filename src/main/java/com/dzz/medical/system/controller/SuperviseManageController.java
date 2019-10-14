package com.dzz.medical.system.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.domain.dto.SuperviseUserListParam;
import com.dzz.medical.supervise.service.SuperviseUserService;
import com.dzz.medical.supervise.service.impl.SuperviseUserServiceMongoImpl;
import com.dzz.medical.util.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/system/supervise")
@Api(value = "监督用户管理", tags = "2、监督用户管理")
@Slf4j
public class SuperviseManageController extends BaseController {

    private SuperviseUserService superviseUserService;

    @Autowired
    public void setWebsiteUserService(SuperviseUserServiceMongoImpl superviseUserService) {
        this.superviseUserService = superviseUserService;
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
