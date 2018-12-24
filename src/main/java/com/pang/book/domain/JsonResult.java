package com.pang.book.domain;

/**
 * 返回的状态
 * @param <T> 任意数据类型
 */
public class JsonResult<T> {
    private T a;
    private String code;
    private String msg;
    private boolean success;

    public JsonResult() {
    }

    public T getA() {
        return a;
    }

    public JsonResult<T> setA(T a) {
        this.a = a;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JsonResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
