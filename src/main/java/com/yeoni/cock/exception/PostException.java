package com.yeoni.cock.exception;

public class PostException extends BusinessException {
    public PostException(String message) {
        super(message);
    }

    public PostException(int status, String message) {
        super(status, message);
    }
}
