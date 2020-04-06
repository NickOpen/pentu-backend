package com.pentu.exception.base;

public class BaseException extends Exception{
    protected Integer errorCode;
    protected String errorMsg;
    protected String[] parameters;

    public BaseException() {
    }

    public BaseException(String errorMsg){
        this.errorCode = -1;
        this.errorMsg = errorMsg;
    }


    public BaseException(Integer errorCode, String errorMsg, String... parameters) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.parameters = parameters;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String[] getParameters() {
        return parameters;
    }
}