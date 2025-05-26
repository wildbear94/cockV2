package com.yeoni.cock.exception;

public class BoardException extends BusinessException {
    public BoardException(String message) {
        super(message);
    }

    public BoardException(int status, String message) {
        super(status, message);
    }
}
