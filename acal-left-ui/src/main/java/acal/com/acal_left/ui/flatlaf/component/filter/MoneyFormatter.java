package acal.com.acal_left.ui.flatlaf.component.filter;

import javax.swing.text.*;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormatter extends DocumentFilter {
    private final NumberFormat format = NumberFormat.getCurrencyInstance( Locale.of("pt", "BR"));

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        parseAndFormat(fb, string);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        parseAndFormat(fb, text);
    }

    private void parseAndFormat(FilterBypass fb, String text) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String digits = (currentText + text).replaceAll("\\D", "");

        if (digits.isEmpty()) {
            fb.replace(0, fb.getDocument().getLength(), format.format(0), null);
            return;
        }

        double value = Double.parseDouble(digits) / 100;
        fb.replace(0, fb.getDocument().getLength(), format.format(value), null);
    }
}