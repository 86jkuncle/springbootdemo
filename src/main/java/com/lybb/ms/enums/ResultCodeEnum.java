package com.lybb.ms.enums;

import com.lybb.ms.result.IResult;

public enum ResultCodeEnum implements IResult {

    SUCCESS(200,"操作成功",200),
    FAILED(500, "操作失败",500),
    PRAM_NOT_MATCH(400, "参数不正确",400),
    VALIDATE_FAILED(400, "参数检验失败",400),
    UNAUTHORIZED(401, "未登录或token过期，请登录！",401),
    FORBIDDEN(403, "没有相关权限",403),
    NOT_FOUND(404, "没有找到相关数据",404),
    ERROR(500, "系统异常",500),
    ;

    private long code;
    private String message;
    private int status;

    ResultCodeEnum(long code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }


    @Override
    public long code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public int status() {
        return status;
    }
}
