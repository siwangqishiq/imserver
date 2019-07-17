package com.xinlan.service;

import com.xinlan.model.Account;
import com.xinlan.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerContext {
    private static transient ServerContext mInstance;

    private UserService userService;

    private Map<String , Account> userDataCache;

    private ServerContext(){
        initService();

        userDataCache = new ConcurrentHashMap<String, Account>();
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

    public Map<String , Account> getUserDataCache(String account){
        return userDataCache;
    }

    public void putAccount(Account account){
        if(account == null)
            return;
        userDataCache.put(account.getUid() , account);
    }

    public Account getAccountByUid(final String uid){
        return userDataCache.get(uid);
    }
}//end class
