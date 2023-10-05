package com.invisibleprogrammer.invisibletirapi.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTests {
    final static PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    public void test_password_is_valid() {
        assertTrue(passwordValidator.isValid("12MyPassword_123!"));
    }

    @Test
    public void test_password_too_short() {
        assertFalse(passwordValidator.isValid("123"));
    }

    @Test
    public void test_password_no_numeric_character() {
        assertFalse(passwordValidator.isValid("MyPasswordWithoutNumericCharacters"));
    }

    @Test
    public void test_password_no_uppercase() {
        assertFalse(passwordValidator.isValid("12mypassword_123!"));
    }

    @Test
    public void test_password_no_special_character() {
        assertFalse(passwordValidator.isValid("12mypassword12345"));
    }
}
