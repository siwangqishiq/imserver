package com.xinlan.util;

import java.util.regex.Pattern;

public final class CheckUtil {
    public static boolean checkAccountMark(String account){
        String all = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";
        Pattern pattern = Pattern.compile(all);
        return pattern.matches(all,account);
    }
}
