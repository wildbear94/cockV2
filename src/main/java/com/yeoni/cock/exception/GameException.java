package com.yeoni.cock.exception;

public class GameException extends BusinessException {
    public GameException(String message) {
        super(message);
    }

    public GameException(int status, String message) {
        super(status, message);
    }
}
