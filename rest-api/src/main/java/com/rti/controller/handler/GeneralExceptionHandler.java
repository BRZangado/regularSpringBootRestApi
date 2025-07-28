package com.rti.controller.handler;

import com.rti.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<DefaultExceptionBody> handleBaseException(final BaseException e) {
        log.error(e.getMessage(), e);
        final DefaultExceptionBody defaultExceptionBody = DefaultExceptionBody.builder()
                .code(String.valueOf(e.getHttpStatus().value()))
                .messageDetails(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(defaultExceptionBody, e.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultExceptionBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        DefaultExceptionBody defaultExceptionBody = new DefaultExceptionBody();
        defaultExceptionBody.setCode(HttpStatus.BAD_REQUEST.name());
        defaultExceptionBody.setMessage("Field request is invalid");

        List<DefaultExceptionBody.Problem> errors = buildErrorsList(e.getBindingResult().getAllErrors());

        if(errors != null && !errors.isEmpty()) {
            defaultExceptionBody.setErrors(errors);
        }

        defaultExceptionBody.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(defaultExceptionBody, HttpStatus.BAD_REQUEST);
    }

    private List<DefaultExceptionBody.Problem> buildErrorsList(List<ObjectError> errors) {
        return errors.stream().map(error -> {
            String name = error.getObjectName();

            if (error instanceof FieldError) {
                name = ((FieldError) error).getField();
            }

            return DefaultExceptionBody.Problem.builder()
                    .name(name)
                    .userMessage(error.getDefaultMessage())
                    .build();
        }).collect(Collectors.toList());
    }
}