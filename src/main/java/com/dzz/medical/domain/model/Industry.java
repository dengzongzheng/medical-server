package com.dzz.medical.domain.model;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 行业
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:37
 */
@Data
@Document(collection = "industry")
public class Industry implements Serializable {

    private static final long serialVersionUID = 4287492969428231859L;

    /**
     * 行业代码
     */
    @Field("code")
    private String code;

    /**
     * 行业名称
     */
    @Field("name")
    private String name;

}
