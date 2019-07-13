package com.xinlan.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xinlan.Config;
import io.netty.util.internal.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

/**
 * 签发token  验证token
 */
public class SecurityHelper {
    private static JWTVerifier verifier;

    static {
        try {
            verifier = JWT.require(HMAC256(Config.ACCOUNT_SCRECT)).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static final String KEY_ACCOUNT = "_account";
    private static final String KEY_PWD = "_pwd";

    public static String createToken(String account, String pwd) {
        if (StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(pwd))
            return null;

        Map<String, Object> headMap = new HashMap<String, Object>();
        headMap.put(KEY_ACCOUNT, account);
        headMap.put(KEY_PWD, pwd);

        String token = null;
        try {
            token = JWT.create().withHeader(headMap)
                    .withExpiresAt(new Date(System.currentTimeMillis() + Config.ACCOUNT_TOKEN_EXPIRE_TIME))
                    .sign(HMAC256(Config.ACCOUNT_SCRECT));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     */
    public static TokenVertifyResult vertifyToken(final String token ,final ICheck check) {
        if (StringUtil.isNullOrEmpty(token))
            return TokenVertifyResult.error_expire;

        try {
            DecodedJWT decodeJWT = verifier.verify(token);
            final String account = decodeJWT.getHeaderClaim(KEY_ACCOUNT).asString();
            final String pwd = decodeJWT.getHeaderClaim(KEY_PWD).asString();

            if(check != null){
                return check.validateAccount(token , account , pwd)
                        ? TokenVertifyResult.success : TokenVertifyResult.error_invalide;
            }else{
                return TokenVertifyResult.success;
            }
        } catch (JWTVerificationException e) {
            return TokenVertifyResult.error_expire;
        } catch (Exception e) {
            return TokenVertifyResult.unknow;
        }
    }

    public interface ICheck{
        boolean validateAccount(final String token , final String account , final String pwd);
    }

    /**
     * 从Token 中获取用户标识
     * @param token
     * @return
     */
    public static String getAccountFromToken(String token) {
        if (vertifyToken(token , null) == TokenVertifyResult.success) {
            DecodedJWT decodeJWT = verifier.verify(token);
            return decodeJWT.getHeaderClaim(KEY_ACCOUNT).asString();
        }
        return null;
    }

    private static boolean validateAccount(String account, String pwd) {
        return true;
    }

}
