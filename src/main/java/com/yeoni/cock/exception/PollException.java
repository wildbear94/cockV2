package com.yeoni.cock.exception;

public class PollException extends BusinessException {
    public PollException(String message) {
        super(message);
    }

    public PollException(int status, String message) {
        super(status, message);
    }
}
