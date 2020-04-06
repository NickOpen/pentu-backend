package com.pentu.config.interceptor;


import com.bx.util.others.BrowserDetect;
import com.pentu.common.anotation.Permit;
import com.pentu.constants.base.Permission;
import com.pentu.exception.auth.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 权限控制拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 权限校验：
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Permit permit = getPermit(handlerMethod);
        //不需要安全验证或者是微信端允许访问的 request 的跳过 token 处理
        if (permit != null) {
            if (!checkPermit(request, permit)) {
                throw new UnAuthorizedException();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) {
    }

    /**
     * 检测是否包含了"所有"权限 或者 包含了微信权限并且当前为微信
     *
     * @param request
     * @param permit
     * @return
     */
    private boolean checkPermit(HttpServletRequest request, Permit permit) {
        boolean hasAllPermission = hasPermit(permit, Permission.ALL);
        boolean hasWeChatPermission = hasPermit(permit, Permission.WECHAT_USER);
        boolean isWeChat = isWeChatUser(request);
        return hasAllPermission || (hasWeChatPermission && isWeChat);
    }

    /**
     * 检测permit是否包含特定的值
     *
     * @param permit
     * @param permission
     * @return
     */
    private boolean hasPermit(Permit permit, String permission) {
        if (null == permit) {
            return false;   //若permit为空，则检验通过
        }
        List<String> permitValues = Arrays.asList(permit.value());
        return permitValues.contains(permission);

    }

    /**
     * 获取当前Handler的Permit 注解
     *
     * @param hm
     * @return
     */
    private Permit getPermit(HandlerMethod hm) {
        Method method = hm.getMethod();
        Class clazz = method.getDeclaringClass();
        Permit resultPermit = null;
        //置于method的Permit优先级高于置于class的注解
        if (method.isAnnotationPresent(Permit.class)) {
            resultPermit = method.getAnnotation(Permit.class);
        } else if (clazz.isAnnotationPresent(Permit.class)) {
            resultPermit = (Permit) clazz.getAnnotation(Permit.class);
        }
        return resultPermit;
    }

    /**
     * 检测用户是否在微信状态下
     *
     * @param request
     * @return
     */
    private boolean isWeChatUser(HttpServletRequest request) {
        return BrowserDetect.isWeChatClient(request.getHeader(HttpHeaders.USER_AGENT));
    }

}
