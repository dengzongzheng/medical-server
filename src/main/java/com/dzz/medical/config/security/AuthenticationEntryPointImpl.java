package com.dzz.medical.config.security;

import com.alibaba.fastjson.JSON;
import com.dzz.medical.common.response.ResponseDzz;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 匿名访问授保护资源时由此类处理
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月06 17:05
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException e) throws IOException {

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(ResponseDzz.fail("权限不足")));
        out.flush();
        out.close();
    }
}
