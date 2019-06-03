package com.dzz.medical.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.vo.NewsDetailVo;
import com.dzz.medical.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外api
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月02 18:29
 */
@RestController
@RequestMapping("/api/news")
@Slf4j
public class WebsiteController {

    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * 消息列表查询
     * @param category 分类
     * @param pageNo 当前页
     * @param pageSize 每页条数
     * @return 结果
     */
    @GetMapping("/list/{category}")
    public ResponseDzz<PageUtil> list(@PathVariable("category") Integer category,
    @RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {

        return newsService.findNewsByCategory(category, pageNo, pageSize);
    }


    /**
     * 详情
     * @param newsNo 编号
     * @return 结果
     */
    @GetMapping("/detail")
    public ResponseDzz<NewsDetailVo> detail(@RequestParam("newsNo") String newsNo){

        return newsService.websiteFindNewsByNewsNo(newsNo);
    }
}
