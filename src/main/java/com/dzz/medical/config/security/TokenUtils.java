package com.dzz.medical.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author dzz
 * @version 1.0.0
 * @since 2019年04月29 17:09
 */
public class TokenUtils {

    //Secret密钥
    private final String SECRET = "auth_chm";

    //token有效期（分钟）
    private final long VALIDATE_MINUTE = 30;

    //加密算法
    private final Algorithm algorithm;

    public TokenUtils() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(SECRET);
    }

    /**
     * 根据用户信息生成token
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        Date now = Date.from(Instant.now());
        Date expiration = Date.from(ZonedDateTime.now().plusMinutes(VALIDATE_MINUTE).toInstant());

        //create jwt
        String jwt = JWT.create()
                .withClaim("authorities", authorities)
                .withSubject(authentication.getName())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
        return jwt;
    }

    /**
     * 认证token有效性
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        if(token==null)
            return false;
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 从token中解析中用户信息
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        String authorityString = decodedJWT.getClaim("authorities").asString();

        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

        if(!StringUtils.isEmpty(authorityString)){
            authorities = Arrays.asList(authorityString.split(","))
                    .stream()
                    .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        }

        CustomizeUser principal = new CustomizeUser(decodedJWT.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
