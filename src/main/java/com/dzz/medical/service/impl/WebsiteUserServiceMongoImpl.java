package com.dzz.medical.service.impl;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.WebsiteUserDetail;
import com.dzz.medical.domain.dto.ListWebsiteUserParamDto;
import com.dzz.medical.domain.model.WebsiteUser;
import com.dzz.medical.domain.vo.WebsiteUserListVo;
import com.dzz.medical.service.WebsiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 注册用户接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:12
 */
@Service
public class WebsiteUserServiceMongoImpl implements WebsiteUserService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public ResponseDzz saveWebsiteUser(WebsiteUser websiteUser) {

        mongoTemplate.save(websiteUser);
        return ResponseDzz.ok();
    }

    @Override
    public ResponseDzz<WebsiteUserListVo> listWebsiteUser(ListWebsiteUserParamDto param) {
        return null;
    }

    @Override
    public ResponseDzz findWebsiteUserByName(String userName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_name").is(userName));

        WebsiteUserDetail websiteUserDetail = mongoTemplate.findOne(query, WebsiteUserDetail.class);
        return ResponseDzz.ok(websiteUserDetail);
    }
}
