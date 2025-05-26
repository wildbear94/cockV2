package com.yeoni.cock.exception;

public class ClubException extends BusinessException {
    public ClubException(String message) {
        super(message);
    }

    public ClubException(int status, String message) {
        super(status, message);
    }
}
