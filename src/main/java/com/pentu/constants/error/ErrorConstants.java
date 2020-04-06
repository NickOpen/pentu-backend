package com.pentu.constants.error;


/**
 * 该类定义错误代码，错误有错误码（error_code）以错误原因(error_msg)构成
 * 其中错误码为6位整数
 * 首位为1的为通用类错误，比如文件不存在等
 * 首位为2的为针对特定业务的错误，比如商品不存在等
 */
public interface ErrorConstants {

    int BAD_REQUEST_CODE = 400;
    String BAD_REQUEST_MESSAGE = "缺少参数%1$s";

    int WRONG_PARAMETER_CODE = 461;
    String WRONG_PARAMETER_MSG = "参数错误 %1$s";

    int UNAUTHORIZED_CODE = 401;
    String UNAUTHORIZED_MSG = "没有权限%1$s";

    int TOKEN_EXPIRE_CODE = 402;
    String TOKEN_EXPIRE_MSG = "token 超时";

    int FORBIDDEN = 403;
    String FORBIDDEN_MSG = "没有权限";

    int RESOURCE_NOT_EXIST_CODE = 404;
    String RESOURCE_NOT_EXIST_MSG = "资源不存在 :%1$s";

    int METHOD_NOT_ALLOWED_CODE = 405;
    String METHOD_NOT_ALLOWED_MESSAGE = "方法不允许访问";

    int CONFLICT_ERROR_CODE = 409;
    String CONFLICT_ERROR_MSG = "已存在：%1$s";

    int INTERNAL_SERVER_ERROR_CODE = 500;
    String INTERNAL_SERVER_ERROR_CODE_MSG = "服务器发生错误";

    int LOGIN_FAIL_CODE = 100001;
    String LOGIN_FAIL_MSG = "账号或密码错误";
}