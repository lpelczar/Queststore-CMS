package com.example.queststore.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    EmailValidator emailValidator;

    @BeforeEach
    private void createInstance() {
        emailValidator = new EmailValidator();
    }

    @Test
    public void validateFalseTest() {
        assertFalse(emailValidator.validate("@.com"));
        assertFalse(emailValidator.validate("@@@.@@.pl"));
    }

    @Test
    public void validateTrueTest() {
        assertTrue(emailValidator.validate("nikodem89.pisal.test@testy.com"));
    }
}