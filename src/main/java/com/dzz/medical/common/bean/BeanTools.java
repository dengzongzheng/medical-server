package com.dzz.medical.common.bean;

import com.dzz.medical.system.domain.dto.SystemUserSaveParam;
import com.dzz.medical.system.domain.model.SystemUser;
import org.springframework.beans.BeanUtils;

/**
 * 实体转换工具
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 10:07
 */
public class BeanTools {

    /**
     * SystemUserSaveParamDto转换成SystemUser
     * @param param param
     * @return 结果
     */
    public static SystemUser convertToSystemUser(SystemUserSaveParam param) {

        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(param, systemUser);
        systemUser.setCreateDate(System.currentTimeMillis());
        systemUser.setUpdateDate(System.currentTimeMillis());
        return systemUser;
    }
}
