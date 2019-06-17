package com.dzz.medical.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * 消息应答处理接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年07月28 下午11:10
 */
public interface MessageEventService {

    /**
     * 消息事件处理
     * @param request request
     * @return 处理结果
     */
    String messageEventHandler(HttpServletRequest request);


    /**
     * 检查签名
     * @param signData 待签名数据
     * @param signature 已签名数据
     * @return 检查结果
     */
    Boolean checkSignature(List<String> signData, String signature);

}