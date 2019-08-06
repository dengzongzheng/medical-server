package com.dzz.medical.config.exception;

import com.dzz.medical.common.response.ResponseDzz;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author dzz
 * @version 1.0.0
 * @since 2019年07月30 11:40
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 设置响应头信息
     * @param response response
     */
    private void setResponse(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Content-Type","application/json;charset=UTF-8");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseDzz defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {

        setResponse(response);
        log.error("出现异常了", e);
        ResponseDzz responseDzz;
        if(e instanceof BusinessException) {
            responseDzz =  ResponseDzz.fail(e.getMessage());
        }else{
            responseDzz =  ResponseDzz.fail("系统开小差了，请稍候再试");
        }
        return responseDzz;
    }



    @ExceptionHandler(AccessDeniedException.class)
    public ResponseDzz handleAccessDeniedException(HttpServletResponse response) {

        setResponse(response);
        return ResponseDzz.fail("无权限访问");
    }

}
