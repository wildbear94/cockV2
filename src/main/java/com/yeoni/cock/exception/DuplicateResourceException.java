package com.yeoni.cock.exception;

public class DuplicateResourceException extends BusinessException {
    public DuplicateResourceException(String resource, String identifier) {
        super(409, String.format("이미 존재하는 %s입니다. (ID: %s)", resource, identifier));
    }

    public DuplicateResourceException(String message) {
        super(409, message);
    }
}
