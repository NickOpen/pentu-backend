package com.pentu.constants.base;

/**
 * 权限定义
 */
public class Permission {

    /**
     * 允许所有用户访问,包括匿名用户
     */
    public static final String ALL = "all";

    /**
     * 微信端用户可以访问
     */
    public static final String WECHAT_USER = "wechat_client";

    /**
     * 只要是认证登录过的用户都可访问
     */
    public static final String AUTH_CHECK = "auth_check";


}
