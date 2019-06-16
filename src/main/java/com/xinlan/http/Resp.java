package com.xinlan.http;

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

    public static Resp error(int code , String msg){
        Resp res = new Resp(code , msg);
        return res;
    }

    public static <T> Resp<T> createSuccess(T data){
        Resp<T> resp = new Resp<T>(StatusCode.CODE_SUCCESS , null);
        resp.setData(data);
        return resp;
    }
}
