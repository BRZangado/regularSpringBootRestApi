package com.rti.controller.validator;

import com.rti.utils.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<Email, String> {

    private final MessageSource messageSource;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if(StringUtils.isEmpty(value)) {
            context.buildConstraintViolationWithTemplate("EMPTY EMAIL").addConstraintViolation();
            return false;
        }

        boolean validEmail = ValidationUtils.isValidEmail(value);
        if(!validEmail) {
            context.buildConstraintViolationWithTemplate("INVALID EMAIL").addConstraintViolation();
            return false;
        }
        return true;
    }
}

