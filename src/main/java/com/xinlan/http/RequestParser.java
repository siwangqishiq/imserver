package com.xinlan.http;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析FullHttpRequest发来的请求参数
 */
public class RequestParser {
    private FullHttpRequest fullReq;

    public RequestParser(FullHttpRequest req) {
        this.fullReq = req;
    }

    public Map<String, String> parse() throws IOException {
        HttpMethod method = fullReq.method();
        Map<String, String> parmMap = new HashMap<String, String>();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(fullReq.uri());
            for (Map.Entry<String, List<String>> entry : decoder.parameters().entrySet()) {
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            }

        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullReq);
            decoder.offer(fullReq);
            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();
            for (InterfaceHttpData parm : parmList) {
                Attribute data = (Attribute) parm;
                parmMap.put(data.getName(), data.getValue());
            }//end for each

        }
        return parmMap;
    }
}
