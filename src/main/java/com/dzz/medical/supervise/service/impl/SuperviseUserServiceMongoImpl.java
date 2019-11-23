package com.dzz.medical.supervise.service.impl;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.bo.SuperviseUserDetailBo;
import com.dzz.medical.supervise.domain.dto.SuperviseUserListParam;
import com.dzz.medical.supervise.domain.model.SuperviseUser;
import com.dzz.medical.supervise.domain.vo.SuperviseUserListVo;
import com.dzz.medical.util.service.IdService;
import com.dzz.medical.supervise.service.SuperviseUserService;
import com.google.common.base.Strings;
import java.util.List;
import java.util.Objects;
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
@SuppressWarnings("ALL")
public class SuperviseUserServiceMongoImpl implements SuperviseUserService {

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
    public ResponseDzz saveSuperviseUser(SuperviseUser websiteUser) {

        websiteUser.setUserNo(idService.getFormatId("us_"));
        ResponseDzz responseDzz = getSuperviseUserByName(websiteUser.getUserName());
        if(responseDzz.checkSuccess() && Objects.nonNull(responseDzz.getData())) {
            return ResponseDzz.fail("用户名" + websiteUser.getUserName() + "已经被使用啦");
        }else{
            mongoTemplate.save(websiteUser);
            return ResponseDzz.ok();
        }
    }

    @Override
    public ResponseDzz listSuperviseUser(SuperviseUserListParam param) {


        PageUtil<SuperviseUserListVo> pageUtil = new PageUtil<>();
        pageUtil.setPageSize(param.getPageSize());
        pageUtil.setPageNo(param.getPageNo());
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!Strings.isNullOrEmpty(param.getContent())) {
            criteria.and("user_name").is(param.getContent());
        }

        query.addCriteria(criteria);
        query.limit(pageUtil.getPageSize());
        List<SuperviseUserListVo> listVoList = mongoTemplate
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()), SuperviseUserListVo.class,
                        COLLECTION_NAME);
        pageUtil.setData(listVoList);
        long count = mongoTemplate.count(query, COLLECTION_NAME);
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }

    @Override
    public ResponseDzz getSuperviseUserByName(String userName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_name").is(userName));

        SuperviseUserDetailBo websiteUserDetail = mongoTemplate.findOne(query, SuperviseUserDetailBo.class, COLLECTION_NAME);
        return ResponseDzz.ok(websiteUserDetail);
    }

    @Override
    public ResponseDzz<SuperviseUserDetailBo> getSuperviseUserByName(String userName, String role) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_name").is(userName).and("role").is(role));

        SuperviseUserDetailBo websiteUserDetail = mongoTemplate.findOne(query, SuperviseUserDetailBo.class, COLLECTION_NAME);
        return ResponseDzz.ok(websiteUserDetail);
    }

    @Override
    public ResponseDzz getSuperviseUserByNo(String userNo) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user_no").is(userNo));
        SuperviseUserDetailBo websiteUserDetail = mongoTemplate
                .findOne(query, SuperviseUserDetailBo.class, COLLECTION_NAME);
        return ResponseDzz.ok(websiteUserDetail);
    }
}
