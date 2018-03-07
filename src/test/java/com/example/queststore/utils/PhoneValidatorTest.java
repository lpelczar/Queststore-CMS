package com.example.queststore.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {
    PhoneValidator phoneValidator;

    @BeforeEach
    private void createInstance() {
        phoneValidator = new PhoneValidator();
    }

    @Test
    public void validateFalseTest() {
        assertFalse(phoneValidator.validate("660662665"));
    }

    @Test
    public void validateTrueTest() {
        assertTrue(phoneValidator.validate("123-456-789"));
    }
}