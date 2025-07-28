package com.rti.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isValidEmail(final String email) {
        final String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        final Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }
}
