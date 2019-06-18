package com.dzz.medical.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.WebsiteUserDetail;
import com.dzz.medical.domain.dto.ListWebsiteUserParamDto;
import com.dzz.medical.service.WebsiteUserService;
import com.dzz.medical.service.impl.WebsiteUserServiceMongoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册用户管理
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月18 15:53
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class WebsiteUserController {

    private WebsiteUserService websiteUserService;

    @Autowired
    public void setWebsiteUserService(WebsiteUserServiceMongoImpl websiteUserService) {
        this.websiteUserService = websiteUserService;
    }

    /**
     * 列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listUsers")
    public ResponseDzz<PageUtil> listUsers(ListWebsiteUserParamDto param){

        return websiteUserService.listWebsiteUser(param);
    }


    /**
     * 详情
     * @param userNo 编号
     * @return 结果
     */
    @GetMapping("/detail")
    public ResponseDzz<WebsiteUserDetail> detail(@RequestParam("userNo") String userNo){

        return websiteUserService.getUserByNo(userNo);
    }
}
