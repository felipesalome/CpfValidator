package cpfvalidator;

public class CpfPreprocessor {
    public String clean(String pCpf) {
        return pCpf.replaceAll("([^\\d])", "");
    }

    public boolean isValidLength(String pCpf) {
        return pCpf.length() == 11;
    }
}
