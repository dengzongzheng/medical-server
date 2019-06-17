package com.dzz.medical.config.wx;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 消息内容等配置
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年08月18 上午8:06
 */
@Configuration
@Data
public class MessageConfig {


    @Value("我们受理的投诉举报职责范围包括：涉及传染病防治、放射卫生、公共场所卫生、学校卫生、生活饮用水卫生、消毒产品和医疗执业等违反卫生法律法规等规定行为。\n"
            + "投诉举报请拨：（0855）4529489\n")
    private String complaintMessage;

}
