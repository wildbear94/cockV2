package com.yeoni.cock.exception;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resource, String identifier) {
        super(404, String.format("%s을(를) 찾을 수 없습니다. (ID: %s)", resource, identifier));
    }

    public ResourceNotFoundException(String message) {
        super(404, message);
    }
}
