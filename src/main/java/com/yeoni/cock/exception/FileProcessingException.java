package com.yeoni.cock.exception;

public class FileProcessingException extends BusinessException {
    public FileProcessingException(String message) {
        super(500, message);
    }

    public FileProcessingException(String message, Throwable cause) {
        super(500, message, cause);
    }
}
