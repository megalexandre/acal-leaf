package acal.com.acal_left.ui.flatlaf.validation.validator;

import acal.com.acal_left.ui.flatlaf.validation.annotation.CpfOrCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, String> {

    @Override
    public void initialize(CpfOrCnpj constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Se vazio, é considerado inválido (use @Optional se quiser permitir null)
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        String clean = value.replaceAll("[^0-9]", "");

        // Se após limpeza ficar vazio, é inválido
        if (clean.isEmpty()) {
            return false;
        }

        if (clean.length() == 11) {
            return isValidCpf(clean);
        } else if (clean.length() == 14) {
            return isValidCnpj(clean);
        }

        return false;
    }

    private boolean isValidCpf(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        int remainder;

        for (int i = 1; i <= 9; i++) {
            sum += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }

        remainder = (sum * 10) % 11;

        if (remainder == 10 || remainder == 11) {
            remainder = 0;
        }

        if (remainder != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }

        sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }

        remainder = (sum * 10) % 11;

        if (remainder == 10 || remainder == 11) {
            remainder = 0;
        }

        return remainder == Integer.parseInt(cpf.substring(10, 11));
    }

    private boolean isValidCnpj(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Integer.parseInt(cnpj.substring(i, i + 1)) * weights1[i];
        }

        int remainder = sum % 11;
        int digit1 = remainder < 2 ? 0 : 11 - remainder;

        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += Integer.parseInt(cnpj.substring(i, i + 1)) * weights2[i];
        }

        remainder = sum % 11;
        int digit2 = remainder < 2 ? 0 : 11 - remainder;

        String calculatedDigits = String.valueOf(digit1) + String.valueOf(digit2);
        String providedDigits = cnpj.substring(12, 14);

        return calculatedDigits.equals(providedDigits);
    }
}

