package com.xinlan.widget;

import com.xinlan.http.action.IAction;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.Set;

public class AnnotateUtilTest {
    @Test
    public void testFindAllHttpComponent(){
        Set<Class> actions = AnnoManageUtil.findAllHttpBeans();
        Assert.assertTrue(actions.size() > 0);
        for(Class clazz : actions){
            System.out.println(clazz.getName());
        }
    }
}
