package com.xinlan.exception;

public class CommonException extends Exception {
    private String errMsg;
    public CommonException(String msg){
        super(msg);
        this.errMsg = msg;
    }

    public String getErrorMsg(){
        return errMsg;
    }
}
