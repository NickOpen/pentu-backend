package com.pentu.constants;

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

    int USER_NOT_EXIST_CODE = 100002;
    String USER_NOT_EXIST_MSG = "用户不存在";

    int WRONG_AUTH_CODE_CODE = 100003;
    String WRONG_AUTH_CODE_MSG = "验证码错误或已过期";

    int USER_LOCKED_CODE = 100004;
    String USER_LOCKED_MSG = "当前用户已锁定";

    int USER_ALREADY_EXIST_CODE = 100005;
    String USER_ALREADY_EXIST_MSG = "用户已存在";

    int PASSWORD_WRONG_CODE = 100006;
    String PASSWORD_WRONG_CODE_MSG = "原密码输⼊错误";

    int PASSWORD_SAME_CODE = 100007;
    String PASSWORD_SAME_CODE_MSG = "新密码与原始密码一样";

    int ERROR_SID_NOT_EXIST_CODE = 200001;
    String ERROR_SID_NOT_EXIST_MSG = "故障码传感器不存在";

    int POINT_TABLE_OCCUPY_BY_FAULT_TABLE_CODE = 200002;
    String POINT_TABLE_OCCUPY_BY_FAULT_TABLE_MSG = "点表已经有关联故障表";

    int ORDER_STATUS_ERROR_CODE = 300001;
    String ORDER_STATUS_ERROR_CODE_MSG = "工单状态错误";

    int ORDER_USER_MATCH_ERROR_CODE = 300002;
    String ORDER_USER_MATCH_ERROR_MSG = "当前用户不是工单负责人";

    int ORDER_USER_SAME_ERROR_CODE = 300003;
    String ORDER_USER_SAME_ERROR_MSG = "工单负责人相同";

    int CAN_NOT_DEL_STATION_WITH_FAN_CODE = 300004;
    String CAN_NOT_DEL_STATION_WITH_FAN_MSG = "无法删除有风机关联的场站";

    int CAN_NOT_DEL_STATION_WITH_USER_CODE = 300005;
    String CAN_NOT_DEL_STATION_WITH_USER_MSG = "无法删除有人员关联的场站";

    int USER_DIFF_STATION_CODE = 300006;
    String USER_DIFF_STATION_MSG = "用户非同一场站";

    int CAN_NOT_DEL_POINT_TABLE_WITH_FAULT_TABLE_CODE = 300007;
    String CAN_NOT_DEL_POINT_TABLE_WITH_FAULT_TABLE_MSG = "无法删除有故障表关联的点表";

    int CAN_NOT_DEL_FAULT_TABLE_WITH_FAN_CODE = 300008;
    String CAN_NOT_DEL_FAULT_TABLE_WITH_FAN_MSG = "无法删除有关联风机的故障表";

    int CAN_NOT_DEL_FAN_FACTORY_WITH_FAN_TYPE_CODE = 300009;
    String CAN_NOT_DEL_FAN_FACTORY_WITH_FAN_TYPE_MSG = "无法删除有关联型号的风机厂家";

    int CAN_NOT_DEL_FAN_TYPE_WITH_POINT_TABLE_CODE = 300010;
    String CAN_NOT_DEL_FAN_TYPE_WITH_POINT_TABLE_MSG = "无法删除有关联点表的风机类型";

    int CONSTRAINT_ERROR_CODE = 310000;
    String CONSTRAINT_ERROR_MSG = "存在关联关系，无法删除";

    int ILLEGAL_FILE_TYPE_ERROR = 422400;
    String ILLEGAL_FILE_TYPE_ERROR_MSG = "非法文件类型";

}
