package queststore.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    private void createInstance() {
        emailValidator = new EmailValidator();
    }

    @Test
    void validateFalseTest() {
        assertFalse(emailValidator.validate("@.com"));
        assertFalse(emailValidator.validate("@@@.@@.pl"));
    }

    @Test
    void validateTrueTest() {
        assertTrue(emailValidator.validate("nikodem89.pisal.test@testy.com"));
    }
}