package com.dzz.medical.domain.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzz.medical.common.enums.WxManageEnums.MessageEvent;
import com.dzz.medical.config.wx.WxConfig;
import com.dzz.medical.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信工具生成
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月17 17:27
 */
@Component
public class WxTools {


    private WxService wxService;

    private WxConfig wxConfig;

    @Autowired
    public void setWxConfig(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    @Autowired
    public void setWxService(WxService wxService) {
        this.wxService = wxService;
    }

    public JSONObject getMenuJSONO() {
        JSONObject menuJSON = new JSONObject();
        JSONObject firstMenuJSON = wxService.createMenuJSONObject("为您服务","","","");
        JSONArray firstMenuJSONSub = new JSONArray();
        JSONObject firstSub1 = wxService.createMenuJSONObject("我要投诉","click","", MessageEvent.COMPLAINT.getCode());
        JSONObject firstSub2 = wxService.createMenuJSONObject("办事指南","click","",MessageEvent.GUIDE.getCode());
        JSONObject firstSub3 = wxService.createMenuJSONObject("法律法规","view",wxConfig.getMServerPath() + "/news","");
        firstMenuJSONSub.add(firstSub1);
        firstMenuJSONSub.add(firstSub2);
        firstMenuJSONSub.add(firstSub3);
        firstMenuJSON.put("sub_button", firstMenuJSONSub);

        JSONObject secondMenuJSON = wxService.createMenuJSONObject("卫监播报","","","");
        JSONArray secondMenuJSONSub = new JSONArray();
        JSONObject secondSub1 = wxService.createMenuJSONObject("通知公告","view",wxConfig.getMServerPath() + "/news","");
        JSONObject secondSub2 = wxService.createMenuJSONObject("工作动态","view",wxConfig.getMServerPath() + "/news","");
        JSONObject secondSub3 = wxService.createMenuJSONObject("卫生知识","view",wxConfig.getMServerPath() + "/news","");
        JSONObject secondSub4 = wxService.createMenuJSONObject("关于我们","view",wxConfig.getMServerPath() + "/aboutUs","");
        secondMenuJSONSub.add(secondSub1);
        secondMenuJSONSub.add(secondSub2);
        secondMenuJSONSub.add(secondSub3);
        secondMenuJSONSub.add(secondSub4);
        secondMenuJSON.put("sub_button", secondMenuJSONSub);

        JSONObject thirdMenuJSON = wxService.createMenuJSONObject("诚信自律","view",wxConfig.getMServerPath() +"/supervise","");

        JSONArray buttonArray = new JSONArray();
        buttonArray.add(firstMenuJSON);
        buttonArray.add(secondMenuJSON);
        buttonArray.add(thirdMenuJSON);

        menuJSON.put("button", buttonArray);

        return menuJSON;
    }

}
