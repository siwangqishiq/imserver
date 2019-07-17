package com.xinlan.http.action;

import com.xinlan.model.Account;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

public interface IAction {
    /**
     * 请求方法
     * @return
     */
    HttpMethod getMethod();

    /**
     * 请求uri
     * @return
     */
    String uri();

    /**
     * 是否需要校验
     * @return
     */
    boolean needVertified();

    /**
     * 响应逻辑
     * @param request
     * @param response
     */
    void service(FullHttpRequest request , DefaultFullHttpResponse response);

    /**
     * 获取Account
     * @return
     */
    Account getAccount();

    /**
     * 验证后设置当前用户
     * @param account
     */
    void setAccount(Account account);
}
