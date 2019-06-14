package com.xinlan.security;

/**
 * 验证结果
 */
public enum TokenVertifyResult {
    success, //成功
    error_expire,  //超时
    error_invalide, //不合法
    unknow, //不知道啥错误
}//end class
