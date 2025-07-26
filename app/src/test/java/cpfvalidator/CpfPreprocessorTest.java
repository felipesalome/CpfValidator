package cpfvalidator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

class CpfPreprocessorTest {
    static CpfPreprocessor instance;

    @BeforeAll static void begin() {
        instance = new CpfPreprocessor();
    }

    @ParameterizedTest()
    @ValueSource(strings = {"815.997.240-73", "815&997$240@73#", "  815_997A2 40!7çç3"})
    void shouldCleanNonDigitCaracters(String pCpf) {
        String expected = "81599724073";

        String actual = instance.clean(pCpf);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "true, 81599724073",
        "false, 111",
        "false, 111222333444"
    })
    void shouldReturnFalseWhenCpfLengthIsInvalid(boolean pExpected, String pCpf) {
        boolean actual = instance.isValidLength(pCpf);

        assertEquals(pExpected, actual);
    }
}
