package com.dzz.medical.service;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.domain.model.UserSupervise;
import java.util.Date;

/**
 * 用户问题回答接口
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 17:58
 */
public interface UserSuperviseService {

    /**
     * 保存用户答题信息
     * @param question 题目及答案信息
     * @return 结果
     */
    ResponseDzz saveUserSupervise(UserSupervise question);


    /**
     * 用户答题列表信息查询
     * @param userName 用户名
     * @param createTime 答题时间
     * @return 结果
     */
    ResponseDzz listUserSupervise(String userName, Date createTime);
}