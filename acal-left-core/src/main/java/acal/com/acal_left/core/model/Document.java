package acal.com.acal_left.core.model;

import lombok.Builder;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@Builder
public class Document {

    private static final int CPF_LENGTH = 11;
    private static final String CPF_MASK = "$1.$2.$3-$4";
    private static final Pattern CPF_PATTERN = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");

    private static final int CNPJ_LENGTH = 14;
    private static final String CNPJ_MASK = "$1.$2.$3/$4-$5";
    private static final Pattern CNPJ_PATTERN = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");

    private static final Pattern NON_DIGIT_PATTERN = Pattern.compile("\\D");

    private final String value;

    public String formatted() {
        String digits = getNumber();

        if (digits == null || digits.isEmpty()) {
            return "";
        }

        if (digits.length() == CPF_LENGTH) {
            return CPF_PATTERN.matcher(digits).replaceAll(CPF_MASK);
        }

        if (digits.length() == CNPJ_LENGTH) {
            return CNPJ_PATTERN.matcher(digits).replaceAll(CNPJ_MASK);
        }

        return digits;
    }

    public String getNumber() {
        if (value == null) {
            return null;
        }
        return NON_DIGIT_PATTERN.matcher(value).replaceAll("");
    }

}
