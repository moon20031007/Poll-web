package com.poll.result;

import lombok.Getter;
import lombok.Setter;

public class Result<T> {

    @Setter
    @Getter
    private Integer code;

    private String message;

    @Setter
    private T data;

    public Result() {
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> error(ResultCode resultCode, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }
}