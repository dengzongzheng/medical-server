package com.dzz.medical.supervise.domain.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 答案
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:31
 */
@Data
@Document(collection = "answer")
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements Serializable {

    private static final long serialVersionUID = -8854556368404548632L;

    /**
     * 答案信息
     */
    @Field("answer")
    private String answer;

    /**
     * 答案序号
     */
    @Field("answer_no")
    private String answerNo;
}
