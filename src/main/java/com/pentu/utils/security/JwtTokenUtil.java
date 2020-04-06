package com.pentu.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtTokenUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final String TOKEN_SECRET = "@@@@Pen_Tu####";
    private static final long EXPIRATION = 7200L; //过期时间为2小时
    private static final long EXPIRATION_REMEMBER = 604800L;  //选择记住我以后过期时间为7天
    private static final String ISS_USER = "baoxiang";


    /**
     * 生成token
     *
     * @param payload      置入token当中的值
     * @param isRememberMe 是否记住我
     * @return jwt token
     */
    public static String createToken(String payload, String roleStr, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        Claims claims = Jwts.claims();
        claims.setId(UUID.randomUUID().toString());
        claims.setIssuer(ISS_USER);
//        claims.setExpiration(new Date(System.currentTimeMillis() + expiration*1000));
        claims.setSubject(payload);
        claims.put("roles", roleStr);
        claims.setIssuedAt(new Date());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, TOKEN_SECRET).compact();

    }


    /**
     * 解析token， 获取置入token的payload
     *
     * @param token jwt token
     * @return token payload
     */
    public static String getPayload(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 解析token, 获取置入其中的roles
     *
     * @param token
     * @return
     */
    public static String getRoles(String token) {
        return (String) getTokenBody(token).get("roles");
    }

    /**
     * 判断token 是否已过期
     *
     * @param token jwt token
     * @return 布尔 是否已过期
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 获取token claims
     *
     * @param token jwt token
     * @return claims
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}