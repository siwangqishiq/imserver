package com.xinlan.http.action;

import com.xinlan.http.RequestParser;
import com.xinlan.http.Resp;
import com.xinlan.model.User;
import com.xinlan.widget.HttpComponent;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

/**
 * 返回一个用户信息
 */
@HttpComponent
public class GetUserAction extends BaseAction {
    private RequestParser parser;

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return "/getuser";
    }

    @Override
    public boolean needVertified() {
        return false;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        User u = new User();
        u.setUid(1001);
        u.setAccount("siwangqishiq");
        u.setNick("死亡骑士");
        Resp<User> resp = Resp.createSuccess(u);

        writeHttpResponse(response , resp);
    }
}//end class
