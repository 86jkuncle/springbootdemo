package com.lybb.ms.enums;

import com.lybb.ms.result.IResult;

public enum OrderResultEnum implements IResult {
    ;

    @Override
    public long code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public int status() {
        return 0;
    }
}
