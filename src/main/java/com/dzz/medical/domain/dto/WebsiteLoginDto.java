package com.dzz.medical.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 登录DTO
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 16:23
 */
@Data
public class WebsiteLoginDto implements Serializable {


    private static final long serialVersionUID = -5469770575230591848L;


    private String userName;


    private String password;
}
