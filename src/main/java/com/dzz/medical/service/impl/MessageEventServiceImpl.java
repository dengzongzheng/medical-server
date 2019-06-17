package com.dzz.medical.service.impl;

import com.dzz.medical.common.enums.WxManageEnums.MessageEvent;
import com.dzz.medical.common.enums.WxManageEnums.MessageTypeEnums;
import com.dzz.medical.config.wx.MessageConfig;
import com.dzz.medical.config.wx.WxConfig;
import com.dzz.medical.domain.bo.WxMessageEventBO;
import com.dzz.medical.domain.common.TextMessage;
import com.dzz.medical.service.IdService;
import com.dzz.medical.service.MessageEventService;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息应答处理接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年07月28 下午11:10
 */
@Service
@Slf4j
public class MessageEventServiceImpl implements MessageEventService {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private IdService idService;

    @Autowired
    private MessageConfig messageConfig;

    @Override
    public String messageEventHandler(HttpServletRequest request) {

        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();
        xStream.autodetectAnnotations(true);
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(request.getInputStream(), writer, StandardCharsets.UTF_8.name());
            String str = writer.toString();
            log.info("接收到的数据为：{}", str);
            xStream.processAnnotations(WxMessageEventBO.class);
            WxMessageEventBO wxMessageEventBO = (WxMessageEventBO) xStream.fromXML(str);
            if (wxMessageEventBO.getMsgType().equalsIgnoreCase(MessageTypeEnums.EVENT.getName())) {

                String eventKey = wxMessageEventBO.getEventKey();
                if (eventKey.equalsIgnoreCase(MessageEvent.COMPLAINT.getCode()) || eventKey
                        .equalsIgnoreCase(MessageEvent.GUIDE.getCode())) {

                    TextMessage textMessage = new TextMessage();
                    BeanUtils.copyProperties(wxMessageEventBO, textMessage);
                    textMessage.setMsgType("text");
                    textMessage.setCreateTime(System.currentTimeMillis());
                    textMessage.setMsgId(idService.getId());
                    textMessage.setFromUserName(wxMessageEventBO.getToUserName());
                    textMessage.setToUserName(wxMessageEventBO.getFromUserName());
                    textMessage.setContent(messageConfig.getComplaintMessage());
                    xStream.processAnnotations(TextMessage.class);
                    String message = xStream.toXML(textMessage);
                    log.info("发送的消息为：{}", message);
                    return message;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }


    @Override
    public Boolean checkSignature(List<String> signDataArray, String signature) {

        Collections.sort(signDataArray);
        StringBuilder decodeString = new StringBuilder(signDataArray.get(0)).append(signDataArray.get(1))
                .append(signDataArray.get(2));
        String codeSha1 = DigestUtils.sha1Hex(decodeString.toString());
        log.info("signature:{},codeSha1:{}", signature, codeSha1);
        return signature.equals(codeSha1);
    }

}
