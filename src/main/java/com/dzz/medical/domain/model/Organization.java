package com.dzz.medical.domain.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 类别行业BO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:36
 */
@Data
@Document(collection = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = -1319798726776199551L;

    /**
     * 类别code
     */
    @Field("organization_code")
    private String organizationCode;

    /**
     * 类别名称
     */
    @Field("organization_name")
    private String organizationName;


    /**
     * 行业列表
     */
    @Field("industries")
    private List<Industry> industries;
}
