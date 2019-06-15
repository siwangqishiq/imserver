package com.xinlan.http.action;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

public class Hello2Action implements IAction {
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String uri() {
        return "/hello/nihao";
    }

    @Override
    public boolean needVertified() {
        return false;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        response.content().writeBytes("Hello World1 你好 世界".getBytes());
    }
}//end class
