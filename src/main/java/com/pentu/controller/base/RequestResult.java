package com.pentu.controller.base;

import java.io.Serializable;

public class RequestResult implements Serializable {
    private static final long serialVersionUID = 1414279654677815082L;


    private int status = 0;
    private String message = "success";
    private Object data;

    public RequestResult() {
    }
    
    public RequestResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RequestResult(Object data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "RequestResult{" +
            "errorCode=" + status +
            ", errorMsg='" + message + '\'' +
            ", data=" + data +
            '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
