package com.xinlan.http.action;

import com.xinlan.model.Account;
import com.xinlan.widget.HttpComponent;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

@HttpComponent
public class HelloAction implements IAction {
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String uri() {
        return "/hello";
    }

    @Override
    public boolean needVertified() {
        return false;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        response.content().writeBytes("Hello World1".getBytes());
    }

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public void setAccount(Account account) {

    }
}//end class
