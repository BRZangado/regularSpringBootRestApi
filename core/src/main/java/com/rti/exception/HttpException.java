package com.rti.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends BaseException {

    private HttpStatus httpStatus;
    private String message;

    public HttpException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

