package com.dzz.medical.config.exception;

/**
 * 业务异常
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月02 10:53
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -81373392890729403L;


    public BusinessException(String message) {
        super(message);
    }
}
