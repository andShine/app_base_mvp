package com.tourong.app.net.response;

/**
 * 返回结果封装
 */

public class Response<T> {

    private int code; // 返回的code
    private T entity; // 具体的数据结果
    private T data; // 具体的数据结果
    private String msg; // message 可用来返回接口的说明

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
