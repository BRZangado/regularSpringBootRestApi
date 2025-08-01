package com.rti.controller.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "Invalid Email.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
