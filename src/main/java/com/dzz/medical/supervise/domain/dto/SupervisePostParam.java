package com.dzz.medical.supervise.domain.dto;

import com.dzz.medical.supervise.domain.model.AnswerQuestion;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 评测信息提交
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年10月14 14:00
 */
@Data
@Slf4j
public class SupervisePostParam implements Serializable {

    private static final long serialVersionUID = 6693503039460354109L;

    /**
     * 题目编号
     */
    @Field("question_no")
    private Integer questionNo;

    /**
     * 题目名称
     */
    @Field("question_name")
    private String questionName;


    /**
     * 题目类型code
     * @see com.dzz.medical.common.enums.QuestionType code
     */
    @Field("question_type")
    private Integer questionType;

    /**
     * 题目类型名称
     * @see com.dzz.medical.common.enums.QuestionType name
     */
    @Field("question_type_name")
    private String questionTypeName;


    /**
     * 答案列表
     */
    @Field("answers")
    private List<AnswerQuestion> answers;
}
