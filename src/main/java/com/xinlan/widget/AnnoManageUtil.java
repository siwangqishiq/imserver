package com.xinlan.widget;

import com.xinlan.http.action.IAction;
import org.reflections.Reflections;

import java.util.Set;

public final class AnnoManageUtil {
    private static final String HTTP_ACTION_PACKAGE = "com.xinlan.http.action";

    public static  Set<Class> findAllClassByAnnocation(String packageName,Class annotateClazz){
        Reflections reflections = new Reflections(packageName);
        Set<Class>  classesSet= reflections.getTypesAnnotatedWith(annotateClazz);
        return classesSet;
    }

    public static Set<Class> findAllHttpBeans(){
        return findAllClassByAnnocation(HTTP_ACTION_PACKAGE , HttpComponent.class);
    }
}
