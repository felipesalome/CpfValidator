package cpfvalidator;

public class CpfValidator {

    private static final int CPF_FIRST_DIGIT_VERIFIER = 9;
    private static final int CPF_SECOND_DIGIT_VERIFIER = 10;

    private final CpfPreprocessor preprocessor;
    private final CpfRepeatedDigitsValidator repeatedDigitsValidator;
    private final CpfCheckDigitCalculator checkDigitCalculator;

    public CpfValidator() {
        preprocessor = new CpfPreprocessor();
        repeatedDigitsValidator = new CpfRepeatedDigitsValidator();
        checkDigitCalculator = new CpfCheckDigitCalculator();
    }

    public boolean isValid(String pCpf) {
        if (pCpf == null || pCpf.isEmpty()) {
            return false;
        }

        String cpfSanitized = preprocessor.clean(pCpf);

        if (!preprocessor.isValidLength(cpfSanitized)) {
            return false;
        }

        if (repeatedDigitsValidator.hasAllDigitsEqual(cpfSanitized)) {
            return false;
        }

        String firstCheckDigit = String.valueOf(checkDigitCalculator.calculateFirstDigit(cpfSanitized));
        if (!firstCheckDigit.equals(cpfSanitized.substring(CPF_FIRST_DIGIT_VERIFIER, CPF_SECOND_DIGIT_VERIFIER))) {
            return false;
        }

        String secondCheckDigit = String.valueOf(checkDigitCalculator.calculateSecondDigit(cpfSanitized));
        return secondCheckDigit.equals(cpfSanitized.substring(CPF_SECOND_DIGIT_VERIFIER));
    }
}
