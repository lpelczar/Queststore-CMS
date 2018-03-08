package com.example.queststore.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerCheckerTest {

    @Test
    void isIntegerFalseTest() {
        assertFalse(IntegerChecker.isInteger("no"));
        assertFalse(IntegerChecker.isInteger("12no"));
    }

    @Test
    void isIntegerTrueTest() {
        assertTrue(IntegerChecker.isInteger("512"));
    }
}