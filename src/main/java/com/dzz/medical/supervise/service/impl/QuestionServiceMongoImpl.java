package com.dzz.medical.supervise.service.impl;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.model.Question;
import com.dzz.medical.supervise.service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 题目接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:42
 */
@Service
public class QuestionServiceMongoImpl implements QuestionService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public ResponseDzz saveQuestion(Question question) {
        mongoTemplate.save(question);
        return ResponseDzz.ok();
    }

    @Override
    public ResponseDzz listQuestion(Integer belongOrganization) {

        Query query = new Query();
        query.addCriteria(Criteria.where("belongOrganization").is(belongOrganization));

        List<Question> questionList = mongoTemplate.find(query, Question.class, "question");
        return ResponseDzz.ok(questionList);
    }
}
