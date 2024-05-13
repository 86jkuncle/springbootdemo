package com.lybb.ms.result;

import com.lybb.ms.enums.ResultCodeEnum;
import java.io.Serializable;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 15:22 $
*/
public class CommonResult<T>  {
    private long code;
    private int status;
    private String message;
    private T data;

    public  CommonResult(){

    }

    protected CommonResult(long code,String message,int status,T data){
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }




    public static <T> CommonResult<T> success(){
        return new CommonResult<T>(ResultCodeEnum.SUCCESS.code(),
            ResultCodeEnum.SUCCESS.message(),
            ResultCodeEnum.SUCCESS.status(),
            null);
    }

//    public static <T> CommonResult<T> success(String message){
//        return new CommonResult<T>(ResultCodeEnum.SUCCESS.code(),
//            message,
//            ResultCodeEnum.SUCCESS.status(),
//            null);
//    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCodeEnum.SUCCESS.code(),
            ResultCodeEnum.SUCCESS.message(),
            ResultCodeEnum.SUCCESS.status(),
            data);
    }

    public static <T> CommonResult<T> success(IResult result){
        return new CommonResult<T>(result.code(),
            result.message(),
            result.status(),
            null);
    }


    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<T>(ResultCodeEnum.SUCCESS.code(),
            message,
            ResultCodeEnum.SUCCESS.status(),
            data);
    }

    public static <T> CommonResult<T> success(IResult result,String message){
        return new CommonResult<T>(result.code(),
            message,
            result.status(),
            null);
    }

    public static <T> CommonResult<T> success(IResult result,T data){
        return new CommonResult<T>(result.code(),
            result.message(),
            result.status(),
            data);
    }

    public static <T> CommonResult<T> success(IResult result,String message,T data){
        return new CommonResult<T>(result.code(),
            message,
            result.status(),
            data);
    }

    public static <T> CommonResult<T> failed(long code,String message,int status){
        return new CommonResult<T>(code,message,status,null);
    }

    public static <T> CommonResult<T> failed(IResult result){
        return new CommonResult<T>(result.code(),
            result.message(),
            result.status(),
            null);
    }

    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCodeEnum.FAILED.code(),
            message,
            ResultCodeEnum.FAILED.status(),
            null);
    }

    public static <T> CommonResult<T> failed(IResult result,String message){
        return new CommonResult<T>(result.code(),
            message,
            result.status(),
            null);
    }

    public static <T> CommonResult<T> failed(){
        return failed(ResultCodeEnum.FAILED);
    }

    public long getCode() {
        return code;
    }


    public int getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }


    public T getData() {
        return data;
    }

}
