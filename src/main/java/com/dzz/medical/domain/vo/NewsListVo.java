package com.dzz.medical.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 列表信息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年05月26 22:33
 */
@Data
public class NewsListVo implements Serializable {

    private static final long serialVersionUID = -8777534144106325601L;

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
     * 状态：1:正常、2:下线、0:配置中
     */
    @Field("status")
    private Integer status;

    /**
     * 创建时间
     */
    @Field("create_time")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

}
