package com.dzz.medical.system.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.service.SuperviseUserService;
import com.dzz.medical.system.domain.bo.SystemUserBo;
import com.dzz.medical.system.domain.dto.SystemUserListParam;
import com.dzz.medical.system.service.SystemUserService;
import com.dzz.medical.util.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监督管理Controller
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月18 15:53
 */
@RestController
@RequestMapping("/api/system/supervise")
@Api(value = "监督管理", tags = "2、监督管理")
@Slf4j
@SuppressWarnings("ALL")
public class SuperviseManageController extends BaseController {


    private SystemUserService systemUserService;

    private SuperviseUserService superviseUserService;

    @Autowired
    public void setSystemUserService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Autowired
    public void setSuperviseUserService(SuperviseUserService superviseUserService) {
        this.superviseUserService = superviseUserService;
    }

    /**
     * 监督管理列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listSupervise")
    public ResponseDzz<PageUtil> listSupervise(SystemUserListParam param){

        log.info("接收到的参数:{}", param);
        return systemUserService.listSystemUser(param);
    }


    /**
     * 监督管理详情
     * @param superviseNo 编号
     * @return 结果
     */
    @GetMapping("/detail")
    public ResponseDzz<SystemUserBo> detail(@RequestParam("superviseNo") String superviseNo){

        return systemUserService.getUserByNo(superviseNo);
    }

}
