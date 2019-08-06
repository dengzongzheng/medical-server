package com.dzz.medical.controller;

import com.dzz.medical.config.exception.BusinessException;
import org.springframework.validation.BindingResult;

/**
 * 控制层基础处理
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月04 10:01
 */
public class BaseController {

    /**
     * 校验结果处理
     * @param bindingResult 校验结果
     */
    protected void bindResultHandler(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BusinessException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
    }
}
