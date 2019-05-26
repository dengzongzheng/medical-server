package com.dzz.medical.service.impl;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.dto.ListNewsParamDto;
import com.dzz.medical.domain.dto.UpdateNewsParamDto;
import com.dzz.medical.domain.model.News;
import com.dzz.medical.domain.vo.NewsDetailVo;
import com.dzz.medical.domain.vo.NewsListVo;
import com.dzz.medical.service.NewsService;
import com.google.common.base.Strings;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 文单消息接口实现
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月25 20:50
 */
@Service
public class NewsServiceMongoImpl implements NewsService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseDzz saveNews(News news) {

        mongoTemplate.save(news);
        return ResponseDzz.ok();
    }

    @Override
    public ResponseDzz updateNews(UpdateNewsParamDto paramDto) {
        return null;
    }

    @Override
    public ResponseDzz<Boolean> delete(String newsNo) {
        return null;
    }

    @Override
    public ResponseDzz<NewsDetailVo> findNewsByNewsNo(String newsNo) {
        return null;
    }

    @Override
    public ResponseDzz<PageUtil> listNews(ListNewsParamDto param) {

        PageUtil<NewsListVo> pageUtil = new PageUtil<>();
        pageUtil.setPageSize(param.getPageSize());
        pageUtil.setPageNo(param.getPageNo());
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!Strings.isNullOrEmpty(param.getContent())) {
            criteria.and("title").is(param.getContent());
        }
        if(0 != param.getCategoryCode()) {
            criteria.and("category_code").is(param.getCategoryCode());
        }
        query.addCriteria(criteria);
        query.limit(pageUtil.getPageSize());
        List<NewsListVo> listVoList = mongoTemplate
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()), NewsListVo.class, "goods");
        pageUtil.setData(listVoList);
        long count = mongoTemplate.count(query, "news");
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }

    @Override
    public ResponseDzz<PageUtil> findNewsByCategory(Integer categoryCode, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseDzz<PageUtil> searchNews(String param, Integer pageNo, Integer pageSize) {
        return null;
    }
}
