package com.xinlan.http.action;

import com.xinlan.http.RequestParser;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import java.util.Map;


/**
 * 注册
 */
public class RegisterAccountAction extends BaseAction {
    private RequestParser parser;

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return "/createUser";
    }

    @Override
    public boolean needVertified() {
        return false;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        // response.content().writeBytes("Hello World1".getBytes());
        Map<String, String> params = parseParams(request);
        String account = params.get("account");
        String pwd = params.get("pwd");
        System.out.println("account = " + account + "   pwd = " + pwd);

        response.content().writeBytes("创建用户成功！".getBytes());
    }
}//end class
