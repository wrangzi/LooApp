package com.java.looapp.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    // digit + lowercase char + uppercase char + punctuation + symbol
    private static final String PHONE_PATTERN =
            "^([0-9]){3}-([0-9]){4}-([0-9]{4})";

    private static final Pattern pattern = Pattern.compile(PHONE_PATTERN);

    public static boolean isValid(final String phone_Number) {
        Matcher matcher = pattern.matcher(phone_Number);
        return matcher.matches();
    }
}
