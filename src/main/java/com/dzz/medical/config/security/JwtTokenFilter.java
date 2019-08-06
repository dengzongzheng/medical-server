package com.dzz.medical.config.security;

import com.dzz.medical.config.exception.BusinessException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * jwt token过滤器
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月23 20:37
 */
public class JwtTokenFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(request);
        try {
            if(token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch(BusinessException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
//            SecurityContextHolder.clearContext();
//            response.sendError(ex.getHttpStatus().value(), ex.getMessage());
            throw new BusinessException("出现异常");
        }

        filterChain.doFilter(request, response);
    }

}
