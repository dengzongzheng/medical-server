package com.dzz.medical.domain.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import lombok.Data;

/**
 * 文本消息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年08月18 上午7:49
 */
@Data
@XStreamAlias("xml")
public class TextMessage extends MessageBase implements Serializable{

    private static final long serialVersionUID = 6053707942664990977L;

    @XStreamAlias("Content")
    private String content;
}
