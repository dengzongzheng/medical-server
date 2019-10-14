package com.dzz.medical.system.service.impl;


import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.system.domain.bo.SystemUserBo;
import com.dzz.medical.system.domain.dto.SystemUserListParam;
import com.dzz.medical.system.domain.model.SystemUser;
import com.dzz.medical.util.service.IdService;
import com.dzz.medical.system.service.SystemUserService;
import com.google.common.base.Strings;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 用户接口mongo下的实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月29 15:10
 */
@Service
public class SystemUserServiceMongoImpl implements SystemUserService {

    private MongoTemplate mongoTemplate;

    private IdService idService;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    public void setIdService(IdService idService) {
        this.idService = idService;
    }

    @Override
    public ResponseDzz getUserByName(String userName) {

        Criteria criteria = Criteria.where("user_name").is(userName);
        Query query = new Query();
        query.addCriteria(criteria);
        SystemUser user = mongoTemplate.findOne(query, SystemUser.class);
        return ResponseDzz.ok(user);
    }

    @Override
    public ResponseDzz saveUser(SystemUser user) {

        user.setUserNo(idService.getFormatId("SU"));
        mongoTemplate.save(user);
        return ResponseDzz.ok(Boolean.TRUE);
    }

    @Override
    public ResponseDzz<PageUtil> listSystemUser(SystemUserListParam param) {

        PageUtil<SystemUserBo> pageUtil = new PageUtil<>();
        pageUtil.setPageSize(param.getPageSize());
        pageUtil.setPageNo(param.getPageNo());

        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!Strings.isNullOrEmpty(param.getContent())) {
            criteria.and("user_name").is(param.getContent());
        }

        query.addCriteria(criteria);
        query.limit(pageUtil.getPageSize());
        List<SystemUserBo> listVoList = mongoTemplate
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()), SystemUserBo.class,
                        COLLECTION_NAME);
        pageUtil.setData(listVoList);
        long count = mongoTemplate.count(query, COLLECTION_NAME);
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }

    @Override
    public ResponseDzz<SystemUserBo> getUserByNo(String userNo) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_no").is(userNo));
        SystemUserBo systemUserBo = mongoTemplate
                .findOne(query, SystemUserBo.class, COLLECTION_NAME);
        return ResponseDzz.ok(systemUserBo);
    }
}
