package com.dzz.medical.service.impl;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.bo.WebsiteUserDetailBo;
import com.dzz.medical.domain.dto.ListWebsiteUserParamDto;
import com.dzz.medical.domain.model.WebsiteUser;
import com.dzz.medical.domain.vo.WebsiteUserListVo;
import com.dzz.medical.service.IdService;
import com.dzz.medical.service.WebsiteUserService;
import com.google.common.base.Strings;
import java.util.List;
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

    private IdService idService;

    @Autowired
    public void setIdService(IdService idService) {
        this.idService = idService;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public ResponseDzz saveWebsiteUser(WebsiteUser websiteUser) {

        websiteUser.setUserNo(idService.getFormatId("us_"));
        mongoTemplate.save(websiteUser);
        return ResponseDzz.ok();
    }

    @Override
    public ResponseDzz listWebsiteUser(ListWebsiteUserParamDto param) {


        PageUtil<WebsiteUserListVo> pageUtil = new PageUtil<>();
        pageUtil.setPageSize(param.getPageSize());
        pageUtil.setPageNo(param.getPageNo());
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!Strings.isNullOrEmpty(param.getContent())) {
            criteria.and("user_name").is(param.getContent());
        }

        query.addCriteria(criteria);
        query.limit(pageUtil.getPageSize());
        List<WebsiteUserListVo> listVoList = mongoTemplate
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()), WebsiteUserListVo.class, "website_user");
        pageUtil.setData(listVoList);
        long count = mongoTemplate.count(query, "website_user");
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }

    @Override
    public ResponseDzz findWebsiteUserByName(String userName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_name").is(userName));

        WebsiteUserDetailBo websiteUserDetail = mongoTemplate.findOne(query, WebsiteUserDetailBo.class, "website_user");
        return ResponseDzz.ok(websiteUserDetail);
    }

    @Override
    public ResponseDzz getUserByNo(String userNo) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_no").is(userNo));
        WebsiteUserDetailBo websiteUserDetail = mongoTemplate.findOne(query, WebsiteUserDetailBo.class, "website_user");
        return ResponseDzz.ok(websiteUserDetail);
    }
}
