package com.invisibleprogrammer.invisibletirapi.domain;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    // The password must be at least 10 characters, contains numeric characters, minimum 1 uppercase letter [A-Z] and minimum 1 special character
    private static final String PASSWORD_PATTERN =
            "^(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(String password) {
        if (password == null) {
            return false;
        }

        return pattern.matcher(password).matches();
    }
}
