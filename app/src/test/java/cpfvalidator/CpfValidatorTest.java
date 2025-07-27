package cpfvalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CpfValidatorTest {

    private static CpfValidator instance;

    @BeforeAll static void begin() {
        instance = new CpfValidator();
    }

    @Test void shouldReturnCpfIsValid() {
        assertTrue(instance.isValid("04978388007"));
    }

    @Test void shouldReturnCpfIsInvalidWithEmptyValue() {
        assertFalse(instance.isValid(""));
    }

    @Test void shouldReturnCpfIsInvalidWithNullValue() {
        assertFalse(instance.isValid(null));
    }

    @Test void shouldReturnCpfIsInvalidWithCaracters() {
        assertFalse(instance.isValid("564a1a654849asdf"));
    }

    @Test void shouldReturnCpfIsValidWithFormattedValue() {
        assertTrue(instance.isValid("529.982.247-25"));
    }

    @Test void shouldReturnCpfIsInvalidWithRepeatedCharacters() {
        assertFalse(instance.isValid("111.111.111-11"));
    }

    @Test void shouldReturnCpfIsInvalidWithFirstDigitInvalid() {
        assertFalse(instance.isValid("123.456.789-10"));
    }

    @Test void shouldReturnCpfIsInvalidWithSecondDigitInvalid() {
        assertFalse(instance.isValid("652.891.590-38"));
    }
}
