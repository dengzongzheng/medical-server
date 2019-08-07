package com.dzz.medical.util.common.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文本消息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年08月18 上午7:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XStreamAlias("xml")
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage extends MessageBase implements Serializable{

    private static final long serialVersionUID = 6053707942664990977L;

    @XStreamAlias("Content")
    private String content;
}
