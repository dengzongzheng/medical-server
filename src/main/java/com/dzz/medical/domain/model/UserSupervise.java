package com.dzz.medical.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 用户监督结果信息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:49
 */
@Data
@Document(collection = "user_supervise")
@AllArgsConstructor
@NoArgsConstructor
public class UserSupervise implements Serializable {

    private static final long serialVersionUID = -4607900949619840534L;

    /**
     * 用户名
     */
    @Field("user_name")
    private String userName;

    /**
     * 用户所填写的问题及答案
     */
    private List<AnswerQuestion> answerQuestions;


    /**
     * 创建时间
     */
    @Field("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Field("update_time")
    private Date updateTime;
}
