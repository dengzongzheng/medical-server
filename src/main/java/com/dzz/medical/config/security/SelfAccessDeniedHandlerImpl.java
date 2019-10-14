package com.dzz.medical.config.security;

import com.alibaba.fastjson.JSON;
import com.dzz.medical.common.response.ResponseDzz;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 处理无权限异常
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月10 15:42
 */
@Slf4j
public class SelfAccessDeniedHandlerImpl extends OAuth2AccessDeniedHandler {

    @Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) {

		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            out.write(JSON.toJSONString(ResponseDzz.fail("权限不足")));
            out.flush();
		} catch (IOException e) {
			log.error("IO异常了", e);
		}
	}
}
