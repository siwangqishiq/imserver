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
        Assert.assertEquals(SecurityHelper.vertifyToken(token , null) , TokenVertifyResult.success);
    }

    @Test
    public void testVerfifyToken2(){
        String token = "dadasdsadsa";
        System.out.println(SecurityHelper.vertifyToken(token , null));
        Assert.assertNotEquals(SecurityHelper.vertifyToken(token , null) , TokenVertifyResult.success);
    }

    @Test
    public void testTokenGetAccount(){
        String token = SecurityHelper.createToken("siwangqishiq","123456");
        Assert.assertEquals(SecurityHelper.getAccountFromToken(token) , "siwangqishiq");
    }


    @Test
    public void testTokenValidateAccount(){
        final String ACCOUNT = "siwangqishiq";
        final String PWD= "123456";

        final String token = SecurityHelper.createToken(ACCOUNT,PWD);
        Assert.assertEquals(SecurityHelper.vertifyToken(token, new SecurityHelper.ICheck() {
            @Override
            public boolean validateAccount(String t, String account, String pwd) {
                Assert.assertEquals(token , t);
                Assert.assertEquals(ACCOUNT , account);
                Assert.assertEquals(PWD , pwd);
                return true;
            }
        }) , TokenVertifyResult.success);

        Assert.assertEquals(SecurityHelper.vertifyToken(token, new SecurityHelper.ICheck() {
            @Override
            public boolean validateAccount(String t, String account, String pwd) {
                Assert.assertEquals(token , t);
                Assert.assertEquals(ACCOUNT , account);
                Assert.assertEquals(PWD , pwd);
                return false;
            }
        }) , TokenVertifyResult.error_invalide);
    }
}//end class
