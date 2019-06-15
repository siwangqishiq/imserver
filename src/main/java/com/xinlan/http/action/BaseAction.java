package com.xinlan.http.action;

import com.xinlan.http.RequestParser;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseAction implements IAction {

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
}//end class
