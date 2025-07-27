package cpfvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CpfCheckDigitCalculatorTest {
    private static CpfCheckDigitCalculator instance;

    @BeforeAll static void begin() {
        instance = new CpfCheckDigitCalculator();
    }

    @Test void shouldReturnFirstCheckDigit() {
        int expected = 3;

        int actual = instance.calculateFirstDigit("13776789034");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 0})
    void shouldReturnFirstCheckDigitInvalid(int pUnexpected) {
        int actual = instance.calculateFirstDigit("13776789034");

        assertNotEquals(pUnexpected, actual);
    }

    @Test void shouldReturnSecondCheckDigit() {
        int expected = 4;

        int actual = instance.calculateSecondDigit("13776789034");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 8, 9, 0})
    void shouldReturnSecondCheckDigitInvalid(int pUnexpected) {
        int actual = instance.calculateSecondDigit("13776789034");

        assertNotEquals(pUnexpected, actual);
    }

    @Test void shouldReturnZeroForFirstDigit() {
        int expected = 0;

        int actual = instance.calculateFirstDigit("04978388007");

        assertEquals(expected, actual);
    }

    @Test void shouldReturnZeroForSecondDigit() {
        int expected = 0;

        int actual = instance.calculateSecondDigit("75484920060");

        assertEquals(expected, actual);
    }
}
