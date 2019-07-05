package com.xinlan.http;

import com.xinlan.http.action.*;
import com.xinlan.widget.AnnoManageUtil;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Router {
    private Map<String, IAction> routers = new HashMap<String, IAction>();

    public Router(){
        initRouter();
    }

    private void initRouter(){
        Set<Class> actionsClazz = AnnoManageUtil.findAllHttpBeans();
        for(Class clazz : actionsClazz){
            addRouters(clazz);
        }//end for each
    }

    public IAction findAction(FullHttpRequest request){
        if(request == null)
            return null;

        String uri = request.uri();
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        String path = decoder.path();

        System.out.println("uri = " + decoder.uri());
        System.out.println("path = " + decoder.path());
        System.out.println("rawPath = " + decoder.rawPath());
        System.out.println("query = " + decoder.rawQuery());

        IAction valueAction = routers.get(path);
        try {
            if( valueAction != null
                    && valueAction.getMethod() == request.method()){
                Class clazz = valueAction.getClass();
                IAction action = (IAction) clazz.newInstance();
                System.out.println("actionClass = " + action.getClass());
                return action;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void addRouters(Class clazz){
        try {
            IAction action = (IAction)clazz.newInstance();
            routers.put(action.uri() , action);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
