package com.xinlan.http.action;

import com.xinlan.widget.HttpComponent;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import java.util.Map;

/**
 *  创建用户
 *
 *
 *
 */
@HttpComponent
public class CreateUserAction extends BaseAction {

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return "/user/create";
    }

    @Override
    public boolean needVertified() {
        return false;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        Map<String, String> params = parseParams(request);
        
    }
}
