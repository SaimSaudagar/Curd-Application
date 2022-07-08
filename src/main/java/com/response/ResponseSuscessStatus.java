package com.response;

public class ResponseSuscessStatus {

    private int code;
    private String message;
    private Object data;

    public ResponseSuscessStatus() {
    }

    public ResponseSuscessStatus(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseSuscessStatus(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null ;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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


