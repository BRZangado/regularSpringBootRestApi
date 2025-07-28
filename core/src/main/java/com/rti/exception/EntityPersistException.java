package com.rti.exception;

import org.springframework.http.HttpStatus;

public class EntityPersistException extends HttpException{
    public EntityPersistException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
