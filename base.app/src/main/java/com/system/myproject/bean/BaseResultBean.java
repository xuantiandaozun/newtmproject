package com.system.myproject.bean;

/**
 * Created by 18081 on 2018/5/26.
 */

public class BaseResultBean<T> {

    /**
     * code : 0
     * result : {}
     * message : 登录成功
     */

    private String code;
    private T result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
