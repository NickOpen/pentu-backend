package com.pentu.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static com.pentu.constants.SecurityConstants.KEY;

public class JwtTokenUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SECRET = "##PENGTU##";
    public static final String ISS_USER = "pengtu";

    /**
     * 生成Token
     * @param payload
     * @param expiration
     * @return
     */
    public static String createToken(String payload, long expiration){
        Claims claims  = Jwts.claims();
        Key key = Keys.hmacShaKeyFor(KEY.getBytes());

        claims.setId(UUID.randomUUID().toString());
        claims.setIssuer(ISS_USER);
        if(expiration != -1){
            claims.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000));
        }

        claims.setSubject(payload);
        claims.setIssuedAt(new Date());

        return Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    /**
     * 解析token payload.
     * @param token
     * @return
     */
    public static String parsePayload(String token){
        String payload = parseTokenBody(token).getSubject();

        return payload;
    }

    /**
     * 判断Token是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        return parseTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 解析token claims.
     * @param token
     * @return
     */
    private static Claims parseTokenBody(String token){
        Key key = Keys.hmacShaKeyFor(KEY.getBytes());

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
