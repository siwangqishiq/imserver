package com.xinlan.http.resp;

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
}
