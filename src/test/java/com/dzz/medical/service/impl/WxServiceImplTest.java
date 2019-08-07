package com.dzz.medical.service.impl;

import com.dzz.medical.config.wx.WxConfig;
import com.dzz.medical.util.service.WxService;
import com.dzz.medical.util.service.impl.WxServiceImpl;
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

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    public void setWxService(WxServiceImpl wxService) {
        this.wxService = wxService;
    }

    @Test
    public void getAccessToken() {


    }

}