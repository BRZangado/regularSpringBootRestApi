package com.rti.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends HttpException {
    public ResourceNotFoundException(final String errorMessage) {
        super(errorMessage, HttpStatus.NOT_FOUND);
    }
}
