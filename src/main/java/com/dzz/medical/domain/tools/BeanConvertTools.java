package com.dzz.medical.domain.tools;

import com.dzz.medical.common.codec.UserAccountUtil;
import com.dzz.medical.domain.dto.WebsiteLoginDto;
import com.dzz.medical.domain.model.WebsiteUser;
import java.util.Date;
import org.springframework.beans.BeanUtils;

/**
 * 实体Bean转换工具
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月17 09:59
 */
public class BeanConvertTools {

    /**
     * 将WebsiteLoginDto转换成WebsiteUser
     * @param loginDto loginDto
     * @return WebsiteUser
     */
    public static WebsiteUser convertToWebsiteUser(WebsiteLoginDto loginDto) {

        WebsiteUser websiteUser = new WebsiteUser();
        BeanUtils.copyProperties(loginDto, websiteUser);
        websiteUser.setPassword(UserAccountUtil.encodePassword(loginDto.getUserName(), loginDto.getPassword()));
        websiteUser.setUpdateTime(new Date());
        websiteUser.setCreateTime(websiteUser.getUpdateTime());
        return websiteUser;
    }
}
