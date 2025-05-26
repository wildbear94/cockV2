package com.yeoni.cock.exception;

public class ClubMemberException extends BusinessException {
    public ClubMemberException(String message) {
        super(message);
    }

    public ClubMemberException(int status, String message) {
        super(status, message);
    }
}
