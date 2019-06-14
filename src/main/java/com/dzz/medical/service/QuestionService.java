package com.dzz.medical.service;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.model.Question;

/**
 * 题目接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:38
 */
public interface QuestionService {

    /**
     * 保存题目信息
     * @param question 题目信息
     * @return 结果
     */
    ResponseDzz saveQuestion(Question question);


    /**
     * 题目列表信息查询
     * @param belongOrganization 所属于组织机构
     * @return 结果
     */
    ResponseDzz listQuestion(Integer belongOrganization);

}
