package com.lybb.ms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/25 23:30 $
*/
public class ValidatorUtils {

    public static boolean isMobile(String mobileNumber){
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }
}
