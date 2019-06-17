package com.dzz.medical.config.wx;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年06月30 下午2:19
 */
@Data
@Configuration
public class WxConfig {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${wx.serverPath}")
    private String serverPath;

    @Value("${wx.app.token}")
    private String token;

    @Value("${wx.app.encodingAESKey}")
    private String aesKey;

    /**
     * 获取access_token_url
     */
    @Value("${wx.app.access_token.url}")
    private String accessTokenUrl;


    /**
     * 删除菜单地址
     */
    @Value("${wx.app.menu.delete.url}")
    private String deleteMenuUrl;


    /**
     * 创建菜单地址
     */
    @Value("${wx.app.menu.create.url}")
    private String createMenuUrl;


    @Value("http://m.sswjjd.cn")
    private String mServerPath;
}
