package acal.com.acal_left.ui.flatlaf.component.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * DocumentFilter para formatar CPF/CNPJ automaticamente.
 * CPF: 000.000.000-00 (11 digitos)
 * CNPJ: 00.000.000/0000-00 (14 digitos)
 */
public class CpfCnpjDocumentFilter extends DocumentFilter {

    private static final int CPF_LENGTH = 11;
    private static final int CNPJ_LENGTH = 14;

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String safeText = text == null ? "" : text;
        if (!isNumeric(safeText)) {
            return;
        }

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String currentDigits = onlyDigits(currentText);

        int startDigit = countDigitsBefore(currentText, offset);
        int endDigit = countDigitsBefore(currentText, offset + length);

        String newDigits = currentDigits.substring(0, startDigit)
                + onlyDigits(safeText)
                + currentDigits.substring(endDigit);

        if (newDigits.length() > CNPJ_LENGTH) {
            newDigits = newDigits.substring(0, CNPJ_LENGTH);
        }

        fb.replace(0, fb.getDocument().getLength(), formatInput(newDigits), attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String currentDigits = onlyDigits(currentText);

        int startDigit = countDigitsBefore(currentText, offset);
        int endDigit = countDigitsBefore(currentText, offset + length);

        // Quando apaga um separador, remove o digito anterior para manter UX natural de backspace.
        if (startDigit == endDigit && startDigit > 0) {
            startDigit--;
        }

        String newDigits = currentDigits.substring(0, startDigit) + currentDigits.substring(endDigit);
        fb.replace(0, fb.getDocument().getLength(), formatInput(newDigits), null);
    }

    /**
     * Formata um número de CPF ou CNPJ.
     * CPF: 000.000.000-00
     * CNPJ: 00.000.000/0000-00
     */
    private String formatInput(String digits) {
        if (digits == null) {
            return "";
        }

        digits = onlyDigits(digits);
        if (digits.isEmpty()) {
            return "";
        }

        if (digits.length() <= CPF_LENGTH) {
            return formatCpf(digits);
        }
        return formatCnpj(digits.substring(0, Math.min(digits.length(), CNPJ_LENGTH)));
    }

    private String formatCpf(String cpf) {
        if (cpf.length() <= 3) {
            return cpf;
        } else if (cpf.length() <= 6) {
            return cpf.substring(0, 3) + "." + cpf.substring(3);
        } else if (cpf.length() <= 9) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6);
        } else {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        }
    }

    private String formatCnpj(String cnpj) {
        if (cnpj.length() <= 2) {
            return cnpj;
        } else if (cnpj.length() <= 5) {
            return cnpj.substring(0, 2) + "." + cnpj.substring(2);
        } else if (cnpj.length() <= 8) {
            return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5);
        } else if (cnpj.length() <= 12) {
            return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8);
        } else {
            return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
        }
    }

    private int countDigitsBefore(String formatted, int position) {
        int digitCount = 0;
        int safePosition = Math.max(0, Math.min(position, formatted.length()));
        for (int i = 0; i < safePosition; i++) {
            if (Character.isDigit(formatted.charAt(i))) {
                digitCount++;
            }
        }
        return digitCount;
    }

    private String onlyDigits(String text) {
        return text == null ? "" : text.replaceAll("[^0-9]", "");
    }

    private boolean isNumeric(String text) {
        return text == null || text.matches("[0-9]*");
    }
}
