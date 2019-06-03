package com.dzz.medical.controller;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.dto.ListNewsParamDto;
import com.dzz.medical.domain.dto.UpdateNewsParamDto;
import com.dzz.medical.domain.model.News;
import com.dzz.medical.domain.vo.NewsDetailVo;
import com.dzz.medical.service.IdService;
import com.dzz.medical.service.NewsService;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章消息控制层
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月25 20:47
 */
@RestController
@RequestMapping("/manage/api/news")
@Slf4j
public class NewsController {

    private IdService idService;

    private NewsService newsService;

    @Autowired
    public void setIdService(IdService idService) {
        this.idService = idService;
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * 保存数据
     * @return 结果
     */
    @PostMapping("/saveNews")
    public ResponseDzz saveNews(@RequestBody News news) {

        news.setNewsNo(String.valueOf(idService.getId()));
        news.setUpdateTime(new Date());
        news.setCreateTime(news.getUpdateTime());
        news.setUpdater("admin");
        news.setCreator("admin");
        return newsService.saveNews(news);
    }


    /**
     * 列表查询
     * @param param 查询条件
     * @return 结果
     */
    @GetMapping("/listNews")
    public ResponseDzz<PageUtil> listNews(ListNewsParamDto param){

        return newsService.listNews(param);
    }


    /**
     * 详情
     * @param newsNo 编号
     * @return 结果
     */
    @GetMapping("/detail")
    public ResponseDzz<NewsDetailVo> detail(@RequestParam("newsNo") String newsNo){

        return newsService.findNewsByNewsNo(newsNo);
    }

    /**
     * 更新数据
     * @param paramDto 参数
     * @return 结果
     */
    @PostMapping("/updateNews")
    public ResponseDzz updateNews(@RequestBody UpdateNewsParamDto paramDto) {
        return newsService.updateNews(paramDto);
    }


}
