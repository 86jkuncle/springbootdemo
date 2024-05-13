package com.lybb.ms.exception;

import com.lybb.ms.enums.ResultCodeEnum;
import com.lybb.ms.result.CommonResult;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 16:18 $
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public <T> CommonResult<?> defaultExceptionHandler(Exception e){
        if(e instanceof MSException){
            return CommonResult.failed(ResultCodeEnum.FAILED, Objects.requireNonNull(e.getMessage()));
        }else if(e instanceof BindException){
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return CommonResult.failed(allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","))
            );
        }

        return CommonResult.failed(ResultCodeEnum.ERROR,e.getMessage());
    }
}
