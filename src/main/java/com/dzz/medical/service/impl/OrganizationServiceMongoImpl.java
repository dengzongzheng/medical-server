package com.dzz.medical.service.impl;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.OrganizationBo;
import com.dzz.medical.domain.model.Organization;
import com.dzz.medical.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * 机构类别接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:46
 */
@Service
public class OrganizationServiceMongoImpl implements OrganizationService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public ResponseDzz saveOrganization(Organization organization) {

        mongoTemplate.save(organization);
        return ResponseDzz.ok();
    }

    @Override
    public ResponseDzz listOrganization() {

        return ResponseDzz.ok(mongoTemplate.findAll(OrganizationBo.class, "organization"));
    }
}
