package com.java.looapp.Validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN=
            "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,63}){1})$";
    private static final Pattern pattern= Pattern.compile(EMAIL_PATTERN);
    public static boolean isValid(final String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
