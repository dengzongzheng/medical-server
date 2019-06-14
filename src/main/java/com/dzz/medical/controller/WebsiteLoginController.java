package com.dzz.medical.controller;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.dto.WebsiteLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台登录控制层
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:21
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class WebsiteLoginController {


    /**
     * 保存数据
     * @return 结果
     */
    @PostMapping("/websiteRegister")
    public ResponseDzz websiteRegister(@RequestBody WebsiteLoginDto loginDto) {


        return ResponseDzz.ok();
    }



    /**
     * 保存数据
     * @return 结果
     */
    @PostMapping("/websiteLogin")
    public ResponseDzz websiteLogin(@RequestBody WebsiteLoginDto loginDto) {


        return ResponseDzz.ok();
    }

}
