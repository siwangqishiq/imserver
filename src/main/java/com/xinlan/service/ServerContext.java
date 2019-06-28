package com.xinlan.service;

public class ServerContext {
    private static transient ServerContext mInstance;

    private UserService userService;
    private ServerContext(){
        initService();
    }

    protected void initService(){
        userService = new UserService();
    }

    public static ServerContext getInstance(){
        if(mInstance == null){
            synchronized (ServerContext.class){
                if(mInstance == null){
                    mInstance = new ServerContext();
                }
            }
        }

        return mInstance;
    }

    public UserService getUserService(){
        return userService;
    }
}//end class
