package com.pentu.config.filters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.pentu.config.security.JwtTokenUtil;
import com.pentu.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pentu.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.alibaba.fastjson.JSONObject;
import static com.pentu.constants.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private final JsonNodeFactory jsonNodeFactory;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.jsonNodeFactory = JsonNodeFactory.instance;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            User applicationUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            applicationUser.getUsername(),
                            applicationUser.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();

        String token = JwtTokenUtil.createToken(username, 1000000);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        JsonNode dataNode = jsonNodeFactory.objectNode().put("token", token)
                .put("username", username);
        JsonNode jsonNode = jsonNodeFactory.objectNode().put("status", 0)
                .put("message", "success")
                .set("data", dataNode);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, jsonNode);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        JSONObject jsonObject = new JSONObject();

        Writer writer = response.getWriter();

        if(failed.getCause() instanceof BaseException){
            BaseException exception = (BaseException) failed.getCause();

            jsonObject.put("status", exception.getErrorCode());
            jsonObject.put("message", exception.getErrorMsg());
        }else{
            jsonObject.put("status", 100001);
            jsonObject.put("message", "账号或密码错误");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, jsonObject);
    }
}