package com.xinlan.http.action;

import com.alibaba.fastjson.JSONObject;
import com.xinlan.http.RequestParser;
import com.xinlan.http.Resp;
import com.xinlan.http.StatusCode;
import com.xinlan.model.Account;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseAction implements IAction {
    protected Account mAccount;

    public Map<String,String> parseParams(FullHttpRequest request){
        if(request == null)
            return new HashMap<String, String>();

        RequestParser parser = new RequestParser(request);
        try {
            return parser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>();
    }

    public void writeHttpResponse(DefaultFullHttpResponse response , Resp resp){
        if(response == null)
            return;

        if(resp == null){
            response.content().writeBytes(
                    JSONObject.toJSONString(
                                    Resp.error(StatusCode.CODE_ERROR,StatusCode.DATA_NULL)).getBytes(CharsetUtil.UTF_8));
            return;
        }
        response.content().writeBytes(JSONObject.toJSONString(resp).getBytes(CharsetUtil.UTF_8));
    }

    @Override
    public Account getAccount(){
        return mAccount;
    }

    @Override
    public void setAccount(Account account) {
        mAccount = account;
    }
}//end class
