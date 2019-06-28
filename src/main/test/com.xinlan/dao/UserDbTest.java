package com.xinlan.dao;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class UserDbTest {
    @Test
    public void testConnectDatabase(){
        Connection con = DBUtils.getSqlSession().getConnection();
        Assert.assertNotNull(con);
    }


}//end class
