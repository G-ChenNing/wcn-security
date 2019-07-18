package com.github.wangchenning.exception;

import lombok.Data;

public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6153353424540013363L;

    private String id;


    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
