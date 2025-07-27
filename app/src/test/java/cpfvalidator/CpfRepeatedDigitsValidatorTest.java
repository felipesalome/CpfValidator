package cpfvalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CpfRepeatedDigitsValidatorTest {
    private static CpfRepeatedDigitsValidator instance;

    @BeforeAll static void begin() {
        instance = new CpfRepeatedDigitsValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "00000000000", 
        "11111111111", 
        "22222222222", 
        "33333333333", 
        "44444444444", 
        "55555555555", 
        "66666666666", 
        "77777777777", 
        "88888888888", 
        "99999999999"
    })
    void shouldReturnTrueWhenCpfHasOnlyRepeatedDigits(String pCpf) {
        boolean actual = instance.hasRepeatedDigits(pCpf);

        assertTrue(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0000000000",   // 10 digits
        "111111111111", // 12 digits
        "k2222222222",  // 11 digits
        "3333333333z",  // 11 digits
        "y44444444444", // char + 11 digits
        "55555555555s", // 11 digits + char
        "66666566666",  // 11 digits
        "777777 7777",  // 11 digits
        "80000000008",  // 11 digits
        "91919191919",  // 11 digits
        "aaaaaaaaaaa"   // 11 chars
    })
    void shouldReturnFalseWhenCpfHasntOnlyRepeatedDigits(String pCpf) {
        boolean actual = instance.hasRepeatedDigits(pCpf);

        assertFalse(actual);
    }
}
