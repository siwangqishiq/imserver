package com.xinlan.http;

import com.alibaba.fastjson.JSON;
import io.netty.util.CharsetUtil;

public class Resp<T> {
    private int code;
    private String msg;
    private T data;

    public Resp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static byte[] error(int code , String msg){
        Resp res = new Resp(code , msg);
        return JSON.toJSONString(res).getBytes(CharsetUtil.UTF_8);
    }
}
