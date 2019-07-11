package com.xinlan.dao;

import com.xinlan.exception.CommonException;
import com.xinlan.model.User;
import com.xinlan.service.ServerContext;
import com.xinlan.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class UserServiceTest {
    public Connection con;
    public UserService mUserService;

    @Before
    public void initCon(){
        con = DBUtils.getSqlSession().getConnection();
        mUserService = ServerContext.getInstance().getUserService();
    }

    @Test
    public void testConnectDatabase(){
        Connection con = DBUtils.getSqlSession().getConnection();
        Assert.assertNotNull(con);
    }

    @Test
    public void testAddUser(){
        UserService userService = ServerContext.getInstance().getUserService();
        User user = new User();
        user.setAccount("maolilan");
        user.setNick("毛利兰");
        user.setAge(18);
        user.setState(User.STATE_NORMAL);
        user.setPwd("12345678");
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        long result = userService.addUser(user);
        Assert.assertTrue(result > 0);
    }

    @Test
    public void testQueryUserByUid(){
        User user = new User();
        user.setAccount("maolilan2");
        user.setNick("毛利兰2");
        user.setAge(18);
        user.setState(User.STATE_NORMAL);
        user.setPwd("12345678");
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        long uid = mUserService.addUser(user);
        System.out.println("uid = " + uid);
        user.setUid(uid);

        User u = mUserService.queryUser(uid);
        Assert.assertEquals(uid , u.getUid());
        Assert.assertEquals("maolilan2" , u.getAccount());
        Assert.assertEquals(18 , u.getAge());
        Assert.assertEquals(User.STATE_NORMAL , u.getState());
        Assert.assertEquals("12345678" , u.getPwd());
    }

    @Test
    public void testQueryUserByAccount(){
        User user = new User();
        user.setAccount("maolilan3");
        user.setNick("毛利兰3");
        user.setAge(18);
        user.setState(User.STATE_NORMAL);
        user.setPwd("12345678");
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        long uid = mUserService.addUser(user);
        System.out.println("uid = " + uid);
        user.setUid(uid);

        User u = mUserService.queryUserByAccount(user.getAccount());

        Assert.assertEquals(user.getAccount() , u.getAccount());
        Assert.assertEquals(user.getAge() , u.getAge());
        Assert.assertEquals(user.getState() , u.getState());
        Assert.assertEquals(user.getPwd() , u.getPwd());

        User u2 = mUserService.queryUserByAccount("zheshisha");
        Assert.assertNull(u2);
    }


    @Test
    public void testUserAdd(){
        User user = new User();
        user.setAccount("maolilan");
        user.setNick("毛利兰3");
        user.setAge(18);
        user.setState(User.STATE_NORMAL);
        user.setPwd("12345678");
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        long uid = mUserService.addUser(user);
        System.out.println("uid = " + uid);
        user.setUid(uid);

        boolean abort = false;
        try {
            mUserService.addUser("maolilan" , "123" , "http://ssss" , 1);
        } catch (CommonException e) {
            e.printStackTrace();
            abort = true;
        }
        Assert.assertTrue(abort);

    }
}//end class
