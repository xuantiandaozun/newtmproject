package com.system.tmhsdl.zp01hxdl_vjflt.bean;

public class TMBaseResoultEntity<T> {
    private int code;
    private String msg;
    private T data;

    public int getError_code() {
        return code;
    }

    public void setError_code(int error_code) {
        this.code = error_code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
