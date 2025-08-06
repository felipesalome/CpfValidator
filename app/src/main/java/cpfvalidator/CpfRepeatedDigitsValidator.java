package cpfvalidator;

public class CpfRepeatedDigitsValidator {
    public boolean hasAllDigitsEqual(String pCpf) {
        return pCpf.chars().distinct().count() == 1;
    }
}
