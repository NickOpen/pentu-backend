package com.pentu.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.pentu.constants.error.ErrorConstants;
import com.pentu.dto.security.LoginDTO;
import com.pentu.utils.security.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private final JsonNodeFactory jsonNodeFactory;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jsonNodeFactory = JsonNodeFactory.instance;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                    loginDTO.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO should throw proper exception
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String userMobile = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        List<String> roleList = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            roleList.add(authority.getAuthority());
        }
        String roleStr = StringUtils.join(roleList, ",");

        Writer writer = response.getWriter();
        JsonNode dataNode = jsonNodeFactory.objectNode().put("token",
                JwtTokenUtil.createToken(userMobile, roleStr, false));
        JsonNode jsonNode = jsonNodeFactory.objectNode().put("status", 0)
                .put("message", "success")
                .set("data", dataNode);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, jsonNode);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        int errorCode = ErrorConstants.LOGIN_FAIL_CODE;
        String errMessage = ErrorConstants.LOGIN_FAIL_MSG;
        jsonObject.put("status", errorCode);
        jsonObject.put("message", errMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, jsonObject);
    }
}

