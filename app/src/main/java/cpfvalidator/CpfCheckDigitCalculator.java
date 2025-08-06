package cpfvalidator;

public class CpfCheckDigitCalculator {

    private static final int CPF_MODULUS_DIVISOR = 11;
    private static final int CPF_FIRST_VERIFIER_WEIGHT_START = 10;
    private static final int CPF_SECOND_VERIFIER_WEIGHT_START = 11;
    private static final int CPF_VERIFIER_MINIMUM_REMAINDER = 2;

    public int calculateFirstDigit(String pCpf) {
        return calculateDigit(pCpf, CPF_FIRST_VERIFIER_WEIGHT_START);
    }

    public int calculateSecondDigit(String pCpf) {
        return calculateDigit(pCpf, CPF_SECOND_VERIFIER_WEIGHT_START);
    }

    private int calculateDigit(String pCpf, int pDigitVerifierWeight) {
        int total = 0;
        int digitPosition = 0;

        for (int i = pDigitVerifierWeight; i > 1; i--) {
            int digitValue = Character.getNumericValue(pCpf.charAt(digitPosition++));
            total += (digitValue * i);
        }

        int remainder = total % CPF_MODULUS_DIVISOR;

        return remainder < CPF_VERIFIER_MINIMUM_REMAINDER ? 0 : CPF_MODULUS_DIVISOR - remainder;
    }
}
