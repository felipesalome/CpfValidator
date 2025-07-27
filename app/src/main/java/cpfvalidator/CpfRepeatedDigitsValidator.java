package cpfvalidator;

public class CpfRepeatedDigitsValidator {
    public boolean hasRepeatedDigits(String pCpf) {
        return pCpf.matches("^(\\d)\\1{10}$");
    }
}
