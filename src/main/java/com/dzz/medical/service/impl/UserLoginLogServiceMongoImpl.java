package com.dzz.medical.service.impl;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.dto.UserLoginLogListParamDto;
import com.dzz.medical.domain.model.UserLoginLog;
import com.dzz.medical.service.UserLoginLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 用户登录日志接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 18:53
 */
@Service
public class UserLoginLogServiceMongoImpl implements UserLoginLogService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Boolean save(UserLoginLog param) {
        mongoTemplate.save(param);
        return Boolean.TRUE;
    }

    @Override
    public  ResponseDzz<PageUtil>  listUserLoginLog(UserLoginLogListParamDto param) {

        PageUtil<UserLoginLog> pageUtil = new PageUtil<>();
        pageUtil.setPageSize(param.getPageSize());
        pageUtil.setPageNo(param.getPageNo());

        Query query = new Query();
        Criteria criteria = Criteria.where("user_no").is(param.getUserNo());
        query.addCriteria(criteria);
        query.limit(pageUtil.getPageSize());
        List<UserLoginLog> userLoginLogList = mongoTemplate
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()), UserLoginLog.class,
                        COLLECTION_NAME);
        pageUtil.setData(userLoginLogList);
        long count = mongoTemplate.count(query, COLLECTION_NAME);
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }
}
