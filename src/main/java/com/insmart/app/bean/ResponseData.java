package com.insmart.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ResponseData <T>{
    private String message;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private T data;
    private String description;

    private String token;
    private int status;

    public ResponseData(String message,String token, T data, String description, int status) {
        this.message = message;
        this.data = data;
        this.description = description;
        this.status = status;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
