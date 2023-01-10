package org.yugo.backend.YuGo.exceptions;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

