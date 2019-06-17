package com.dzz.medical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dzz.medical.common.http.HttpService;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.config.wx.WxConfig;
import com.dzz.medical.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信接口相关实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年07月16 下午10:08
 */
@Service
@Slf4j
public class WxServiceImpl implements WxService {


    private WxConfig wxConfig;

    @Autowired
    public void setWxConfig(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    @Override
    public ResponseDzz getAccessToken(String appId, String secret) {

        StringBuilder urlBuilder = new StringBuilder(wxConfig.getAccessTokenUrl());
        urlBuilder.append("grant_type=client_credential").append("&appid=").append(appId).append("&secret=")
                .append(secret);
        log.info("request url:{}", urlBuilder.toString());
        String result = HttpService.sendRequest(urlBuilder.toString(), "get", "");
        log.info("处理结果:{}", result);
        return checkResult(result);
    }

    @Override
    public ResponseDzz deleteMenu() {

        StringBuilder urlBuilder = new StringBuilder(wxConfig.getDeleteMenuUrl());
        ResponseDzz responseDzz = getAccessToken(wxConfig.getAppId(), wxConfig.getAppSecret());
        if (responseDzz.checkSuccess()) {
            JSONObject jsonObject = (JSONObject) responseDzz.getData();
            urlBuilder.append("access_token=").append(jsonObject.getString("access_token"));
            log.info("request url:{}", urlBuilder.toString());
            String result = HttpService.sendRequest(urlBuilder.toString(), "get", "");
            return checkResult(result);
        }else{
            return ResponseDzz.fail("获取access_token失败");
        }
    }

    @Override
    public ResponseDzz createMenu(String menuJsonString) {

        StringBuilder urlBuilder = new StringBuilder(wxConfig.getCreateMenuUrl());
        ResponseDzz responseDzz = getAccessToken(wxConfig.getAppId(), wxConfig.getAppSecret());
        if (responseDzz.checkSuccess()) {
            JSONObject jsonObject = (JSONObject) responseDzz.getData();
            urlBuilder.append("access_token=").append(jsonObject.getString("access_token"));
            log.info("request url:{}", urlBuilder.toString());
            String result = HttpService.sendRequest(urlBuilder.toString(), "post", menuJsonString);
            return checkResult(result);
        }else{
            return ResponseDzz.fail("获取access_token失败");
        }
    }
}
