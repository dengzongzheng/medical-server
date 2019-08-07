package com.dzz.medical.util.service;

import com.dzz.medical.common.page.PageUtil;
import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.util.domain.dto.UserLoginLogListParamDto;
import com.dzz.medical.supervise.domain.model.UserLoginLog;

/**
 * 用户登录日志接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 18:49
 */
public interface UserLoginLogService {

    String COLLECTION_NAME = "user_login_log";

    /**
     * 保存用户登录日志信息
     * @param param 参数
     * @return 结果
     */
    Boolean save(UserLoginLog param);


    /**
     * 用户登录日志列表查询
     * @param param 查询参数
     * @return 查询结果
     */
    ResponseDzz<PageUtil> listUserLoginLog(UserLoginLogListParamDto param);

}
