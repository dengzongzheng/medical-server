package com.dzz.medical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.config.wx.WxConfig;
import com.dzz.medical.domain.tools.WxTools;
import com.dzz.medical.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月17 10:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WxServiceImplTest {

    private WxService wxService;

    private WxTools wxTools;

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    public void setWxTools(WxTools wxTools) {
        this.wxTools = wxTools;
    }

    @Autowired
    public void setWxService(WxServiceImpl wxService) {
        this.wxService = wxService;
    }

    @Test
    public void getAccessToken() {


    }

    @Test
    public void deleteMenu() {


    }

    @Test
    public void createMenu() {

        ResponseDzz responseDzz = wxService.deleteMenu();
        if (responseDzz.checkFail()) {
            log.error("删除菜单失败，不能进行添加菜单。失败原因:{}", responseDzz.getMessage());
            return;
        }
        JSONObject menuJSON = wxTools.getMenuJSONO();
        log.info("菜单新建结果:{}", wxService.createMenu(menuJSON.toJSONString()).toString());
    }
}