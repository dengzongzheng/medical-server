package com.dzz.medical.domain.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 监督题目
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:17
 */
@Data
@Document(collection = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 8261715108374567500L;

    /**
     * 所属机构
     * @see com.dzz.medical.common.enums.OrganizationEnum code
     */
    private Integer belongOrganization;


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
     * 题目编号
     */
    @Field("question_no")
    private Integer questionNo;


    /**
     * 答案列表
     */
    @Field("answers")
    private List<Answer> answers;
}
