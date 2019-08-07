package com.dzz.medical.util.domain.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import lombok.Data;

/**
 * 微信消息BO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年08月25 上午7:55
 */
@XStreamAlias("xml")
@Data
public class WxMessageEventBO implements Serializable{

    private static final long serialVersionUID = -8316559905311400292L;

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Integer createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("Event")
    private String event;

    @XStreamAlias("EventKey")
    private String eventKey;

}
