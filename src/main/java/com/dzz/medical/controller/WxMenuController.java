package com.dzz.medical.controller;

import com.dzz.medical.domain.tools.WxTools;
import com.dzz.medical.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号菜单处理
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月17 17:22
 */
@RestController
@RequestMapping("/api/news")
@Slf4j
public class WxMenuController {

    private WxService wxService;

    @Autowired
    private WxTools wxTools;

    @Autowired
    public void setWxService(WxService wxService) {
        this.wxService = wxService;
    }

    /**
     * 创建菜单信息
     */
    @RequestMapping(value = "/createMenu", method = RequestMethod.GET)
    public void createMenu() {

        wxService.createMenu(wxTools.getMenuJSONO().toJSONString());
    }


}
