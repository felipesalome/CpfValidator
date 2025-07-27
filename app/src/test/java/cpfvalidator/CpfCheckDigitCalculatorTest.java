package cpfvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CpfCheckDigitCalculatorTest {
    @Test void shouldReturnFirstCheckDigit() {
        int expected = 3;

        CpfCheckDigitCalculator instance = new CpfCheckDigitCalculator();
        int actual = instance.calculateFirstDigit("13776789034");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 0})
    void shouldReturnFirstCheckDigitInvalid(int pUnexpected) {
        CpfCheckDigitCalculator instance = new CpfCheckDigitCalculator();
        int actual = instance.calculateFirstDigit("13776789034");

        assertNotEquals(pUnexpected, actual);
    }

    @Test void shouldReturnSecondCheckDigit() {
        int expected = 4;

        CpfCheckDigitCalculator instance = new CpfCheckDigitCalculator();
        int actual = instance.calculateSecondDigit("13776789034");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 8, 9, 0})
    void shouldReturnSecondCheckDigitInvalid(int pUnexpected) {
        CpfCheckDigitCalculator instance = new CpfCheckDigitCalculator();
        int actual = instance.calculateSecondDigit("13776789034");

        assertNotEquals(pUnexpected, actual);
    }
}
