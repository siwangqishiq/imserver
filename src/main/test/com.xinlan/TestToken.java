package com.xinlan;

import com.xinlan.security.SecurityHelper;
import com.xinlan.security.TokenVertifyResult;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestToken {
    @Test
    public void testCreateToken(){
        String token = SecurityHelper.createToken("siwangqishiq","123456");
        Assert.assertNotNull(token);
    }

    @Test
    public void testVerfifyToken(){
        String token = SecurityHelper.createToken("siwangqishiq","123456");
        Assert.assertEquals(SecurityHelper.vertifyToken(token) , TokenVertifyResult.success);
    }

    @Test
    public void testVerfifyToken2(){
        String token = "dadasdsadsa";
        System.out.println(SecurityHelper.vertifyToken(token));
        Assert.assertNotEquals(SecurityHelper.vertifyToken(token) , TokenVertifyResult.success);
    }

    @Test
    public void testTokenGetAccount(){
        String token = SecurityHelper.createToken("siwangqishiq","123456");
        Assert.assertEquals(SecurityHelper.getAccountFromToken(token) , "siwangqishiq");
    }
}//end class
