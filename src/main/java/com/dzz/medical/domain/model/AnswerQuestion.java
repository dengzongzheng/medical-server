package com.dzz.medical.domain.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 答题信息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "answer_question")
public class AnswerQuestion extends Question{

    private static final long serialVersionUID = 1535719132540944299L;

    /**
     * 用户所填写的答案
     */
    @Field("input_answers")
    private List<Answer> inputAnswers;


    /**
     * 答案附带的图片
     */
    private List<String> answerImages;
}
