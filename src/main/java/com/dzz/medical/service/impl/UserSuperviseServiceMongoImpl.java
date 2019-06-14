package com.dzz.medical.service.impl;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.model.UserSupervise;
import com.dzz.medical.service.UserSuperviseService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 用户答题接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 18:02
 */
@Service
public class UserSuperviseServiceMongoImpl implements UserSuperviseService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public ResponseDzz saveUserSupervise(UserSupervise question) {

        mongoTemplate.save(question);
        return ResponseDzz.ok();
    }

    @Override
    public ResponseDzz listUserSupervise(String userName, Date createTime) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_name").is(userName).and("create_time").is(createTime));

        List<UserSupervise> superviseList = mongoTemplate.find(query, UserSupervise.class, "user_supervise");
        return ResponseDzz.ok(superviseList);
    }
}
