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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

        Query query = new Query();
        query.addCriteria(Criteria.where("newsNo").is(newsNo));
        NewsDetailVo newsDetailVo = mongoTemplate.findOne(query, NewsDetailVo.class, "news");
        return ResponseDzz.ok(newsDetailVo);
    }

    @Override
    public ResponseDzz<NewsDetailVo> websiteFindNewsByNewsNo(String newsNo) {

        Query query = new Query();
        Criteria criteria = Criteria.where("newsNo").is(newsNo);
        query.addCriteria(criteria);
        Update update = new Update();
        update.inc("visit_count", 1);
        mongoTemplate.updateFirst(query,update, "news");

        return findNewsByNewsNo(newsNo);
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
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()), NewsListVo.class, "news");
        pageUtil.setData(listVoList);
        long count = mongoTemplate.count(query, "news");
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }

    @Override
    public ResponseDzz<PageUtil> findNewsByCategory(Integer categoryCode, Integer pageNo, Integer pageSize) {

        PageUtil<NewsListVo> pageUtil = new PageUtil<>();
        pageUtil.setPageSize(pageSize);
        pageUtil.setPageNo(pageNo);
        Sort sort = new Sort(Direction.ASC, "update_date");
        Query query = new Query();
        Criteria porcelain = Criteria.where("category_code").is(categoryCode);
        query.addCriteria(porcelain);
        query.limit(pageUtil.getPageSize());
        List<NewsListVo> list = mongoTemplate
                .find(query.skip((pageUtil.getPageNo() - 1) * pageUtil.getPageSize()).with(sort), NewsListVo.class,
                        "news");
        pageUtil.setData(list);
        long count = mongoTemplate.count(query, "news");
        pageUtil.setTotalCount((int) count);
        pageUtil.setTotalPage();
        return ResponseDzz.ok(pageUtil);
    }

    @Override
    public ResponseDzz<PageUtil> searchNews(String param, Integer pageNo, Integer pageSize) {
        return null;
    }
}
