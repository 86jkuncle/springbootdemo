package com.lybb.ms.result;
/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 15:52 $
*/
public interface IResult {

    long code();
    String message();
    int status();
}
