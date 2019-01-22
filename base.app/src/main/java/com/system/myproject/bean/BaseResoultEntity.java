package com.system.myproject.bean;

public class BaseResoultEntity<T> {

    /**
     * code : 1
     * msg : 修改成功
     * time : 1522374390
     * data : null
     */

    private int code;
    private int ret;
    private String msg;
    private String time;
    private T data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
