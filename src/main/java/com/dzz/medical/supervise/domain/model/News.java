package com.dzz.medical.supervise.domain.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 文章、消息信息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月25 20:43
 */
@Data
@Document(collection = "news")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    /**
     * 法律法规编码
     */
    @Field("news_no")
    private String newsNo;

    /**
     * 标题
     */
    @Field("title")
    private String title;

    /**
     * 副标题
     */
    @Field("sub_title")
    private String subTitle;

    /**
     * 标题图片
     */
    @Field("title_images")
    private String titleImages;

    /**
     * 排序
     */
    @Field("sort")
    private Integer sort;

    /**
     * 置顶
     */
    @Field("topping")
    private Integer topping;

    /**
     * 内容
     */
    @Field("text_data")
    private String textData;

    /**
     * 分类code
     * @see com.dzz.medical.common.enums.CategoryEnum code
     */
    @Field("category_code")
    private Integer categoryCode;


    /**
     * 分类
     * @see com.dzz.medical.common.enums.CategoryEnum name
     */
    @Field("category_name")
    private String categoryName;

    /**
     * 访问量
     */
    @Field("visit_count")
    private Integer visitCount = 0;

    /**
     * 状态：1:正常、2:下线、0:配置中
     */
    @Field("status")
    private Integer status;

    /**
     * 创建时间
     */
    @Field("create_time")
    private Date createTime;

    /**
     * 创建者
     */
    @Field("creator")
    private String creator;

    /**
     * 修改时间
     */
    @Field("update_time")
    private Date updateTime;

    /**
     * 修改者
     */
    @Field("updater")
    private String updater;

}
