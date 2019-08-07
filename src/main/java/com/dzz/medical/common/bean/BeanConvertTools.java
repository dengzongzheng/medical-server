package com.dzz.medical.common.bean;

import com.dzz.medical.common.codec.UserAccountUtil;
import com.dzz.medical.supervise.domain.dto.SuperviseUserRegisterParamDto;
import com.dzz.medical.supervise.domain.model.SuperviseUser;
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
    public static SuperviseUser convertToWebsiteUser(SuperviseUserRegisterParamDto loginDto) {

        SuperviseUser websiteUser = new SuperviseUser();
        BeanUtils.copyProperties(loginDto, websiteUser);
        websiteUser.setPassword(UserAccountUtil.encodePassword(loginDto.getUserName(), loginDto.getPassword()));
        websiteUser.setUpdateTime(new Date());
        websiteUser.setCreateTime(websiteUser.getUpdateTime());
        return websiteUser;
    }
}
