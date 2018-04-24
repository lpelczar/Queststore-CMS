package queststore.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    private PhoneValidator phoneValidator;

    @BeforeEach
    private void createInstance() {
        phoneValidator = new PhoneValidator();
    }

    @Test
    void validateFalseTest() {
        assertFalse(phoneValidator.validate("660662665"));
    }

    @Test
    void validateTrueTest() {
        assertTrue(phoneValidator.validate("123-456-789"));
    }
}