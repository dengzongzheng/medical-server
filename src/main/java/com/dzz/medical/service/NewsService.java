package com.dzz.medical.service;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.dto.ListNewsParamDto;
import com.dzz.medical.domain.dto.UpdateNewsParamDto;
import com.dzz.medical.domain.model.News;
import com.dzz.medical.domain.vo.NewsDetailVo;

/**
 * 文章消息接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月25 20:49
 */
public interface NewsService {

    /**
     * 保存
     * @param news news
     * @return 保存结果
     */
    ResponseDzz saveNews(News news);


    /**
     * 更新
     * @param paramDto 更新数据
     * @return 保存结果
     */
    ResponseDzz updateNews(UpdateNewsParamDto paramDto);

    /**
     * 删除数据
     * @param newsNo 编号
     * @return 结果
     */
    ResponseDzz<Boolean> delete(String newsNo);



    /**
     * 详情
     * @param newsNo 编号
     * @return 查询结果
     */
    ResponseDzz<NewsDetailVo> findNewsByNewsNo(String newsNo);



    /**
     * 详情
     * @param newsNo 编号
     * @return 查询结果
     */
    ResponseDzz<NewsDetailVo> websiteFindNewsByNewsNo(String newsNo);

    /**
     * 列表查询
     * @param param 参数
     * @return 结果
     */
    ResponseDzz<PageUtil> listNews(ListNewsParamDto param);


    /**
     * 按分类查
     * @param categoryCode 分类code
     * @param pageNo 页号
     * @param pageSize 每页条数
     * @return 查询结果
     */
    ResponseDzz<PageUtil> findNewsByCategory(Integer categoryCode, Integer pageNo, Integer pageSize);



    /**
     * 搜索
     * @param param 参数
     * @param pageNo 页号
     * @param pageSize 每页条数
     * @return 查询结果
     */
    ResponseDzz<PageUtil> searchNews(String param, Integer pageNo, Integer pageSize);
}