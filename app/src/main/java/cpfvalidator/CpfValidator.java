package cpfvalidator;

public class CpfValidator {

    private static final int CPF_FIRST_DIGIT_VERIFIER = 9;
    private static final int CPF_SECOND_DIGIT_VERIFIER = 10;

    public boolean isValid(String pCpf) {
        if (pCpf == null || pCpf.isEmpty()) {
            return false;
        }

        CpfPreprocessor preprocessor = new CpfPreprocessor();
        String cpfSanitized = preprocessor.clean(pCpf);

        if (!preprocessor.isValidLength(cpfSanitized)) {
            return false;
        }

        CpfRepeatedDigitsValidator repeatedDigitsValidator = new CpfRepeatedDigitsValidator();
        if (repeatedDigitsValidator.hasRepeatedDigits(cpfSanitized)) {
            return false;
        }

        CpfCheckDigitCalculator checkDigitCalculator = new CpfCheckDigitCalculator();
        String firstCheckDigit = String.valueOf(checkDigitCalculator.calculateFirstDigit(cpfSanitized));
        if (!firstCheckDigit.equals(cpfSanitized.substring(CPF_FIRST_DIGIT_VERIFIER, CPF_SECOND_DIGIT_VERIFIER))) {
            return false;
        }

        String secondCheckDigit = String.valueOf(checkDigitCalculator.calculateSecondDigit(cpfSanitized));
        if (!secondCheckDigit.equals(cpfSanitized.substring(CPF_SECOND_DIGIT_VERIFIER))) {
            return false;
        }

        return true;
    }
}
