package com.xinlan.dao;

import com.xinlan.dao.impl.UserDaoImpl;
import com.xinlan.model.User;
import com.xinlan.service.ServerContext;
import com.xinlan.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.IOP.ServiceContext;

import java.sql.Connection;

public class UserServiceTest {
    public Connection con;

    @Before
    public void initCon(){
        con = DBUtils.getSqlSession().getConnection();
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
        long result = userService.addUser(user);
        Assert.assertTrue(result > 0);
    }
}//end class
