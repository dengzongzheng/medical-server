package com.dzz.medical.controller;

import com.dzz.medical.config.wx.WxConfig;
import com.dzz.medical.service.MessageEventService;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信管理控制层
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年06月30 下午2:15
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class WxMessageEventController {

    @Autowired
    private WxConfig wxConfig;



    private MessageEventService messageEventService;

    @Autowired
    public void setMessageEventService(MessageEventService messageEventService) {
        this.messageEventService = messageEventService;
    }

    /**
     * 接收消息,用于处理消息事件
     * @param request request
     * @return 消息处理
     */
    @RequestMapping(value = "/messageEvent", method = {RequestMethod.POST, RequestMethod.GET})
    public void messageEvent(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String messageHandlerResult = "";
        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {

            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echoString = request.getParameter("echostr");
            List<String> tempArray = new ArrayList<>(3);
            tempArray.add(timestamp);
            tempArray.add(nonce);
            tempArray.add(wxConfig.getToken());
            if (messageEventService.checkSignature(tempArray, signature)) {
                messageHandlerResult =  echoString;
            }else{
                messageHandlerResult = "false";
            }
        }else{
            messageHandlerResult = messageEventService.messageEventHandler(request);
        }
        log.info("响应事件报文为：{}",messageHandlerResult);
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.print(messageHandlerResult);
            printWriter.flush();
        } catch (IOException ex) {
            log.error("IO异常了", ex);
        }
    }
}
