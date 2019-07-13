package com.xinlan.http;

public final class StatusCode {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 404;
    public static final int CODE_ERROR_LOGIC = 500;

    public static final String NO_ACTION = "没有对应的服务";
    public static final String DATA_NULL = "data is null";
    public static final String NO_LOGIN = "还未登录";
    public static final String TOKEN_ERROR = "登录状态错误";
    public static final String TOKEN_EXPIRE = "登录状态失效";
    public static final String UNKNOW = "未知错误";

    public static final String LOGIN_PWD_ACCOUNT_ERROR = "用户名或密码错误";
}
