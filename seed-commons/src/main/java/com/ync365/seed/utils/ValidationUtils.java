package com.ync365.seed.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    
    /**
     * 校验字符串是否为全数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
}
