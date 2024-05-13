package com.lybb.ms.exception;

import java.io.Serializable;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 16:27 $
*/
public class MSException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    public MSException() {
    }

    public MSException(String message) {
        super(message);
    }
}
