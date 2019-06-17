package com.dzz.medical.domain.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import lombok.Data;

/**
 * 微信消息基础DTO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年08月18 上午7:46
 */
@Data
public class MessageBase implements Serializable{

    private static final long serialVersionUID = 3546357024801254812L;

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("MsgId")
    private Long msgId;
}
