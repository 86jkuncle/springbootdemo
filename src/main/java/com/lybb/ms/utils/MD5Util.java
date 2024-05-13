package com.lybb.ms.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/25 9:03 $
*/
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String SALT = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass){
        String str = String.valueOf(SALT.charAt(0))+SALT.charAt(2)+inputPass+SALT.charAt(5)+SALT.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt){
        String str = String.valueOf(salt.charAt(0))+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input,String salt){
        String str = inputPassToFormPass(input);
        return formPassToDBPass(str, salt);
    }




































}
