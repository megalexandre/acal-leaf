package acal.com.acal_left.ui.flatlaf.component.filter;

import javax.swing.*;
import javax.swing.text.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyTextField extends JTextField {

    private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public MoneyTextField() {
        super();
        init();
    }

    private void init() {
        setHorizontalAlignment(JTextField.RIGHT);
        setText(FORMAT.format(0));
        ((AbstractDocument) getDocument()).setDocumentFilter(new MoneyDocumentFilter());
    }

    public BigDecimal getBigDecimal() {
        try {
            String text = getText()
                .replaceAll("[R$\\s\\u00A0]", "")   // Remove R$, $, espaços normais e não-quebráveis
                .replaceAll("\\.", "")              // Remove separador de milhares (ponto)
                .replace(",", ".")                  // Troca vírgula decimal por ponto
                .trim();

            if (text.isEmpty()) {
                return BigDecimal.ZERO;
            }

            return new BigDecimal(text);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public void setBigDecimal(BigDecimal value) {
        if (value == null) {
            setText(FORMAT.format(0));
            return;
        }
        setText(FORMAT.format(value));
    }

    private class MoneyDocumentFilter extends DocumentFilter {
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            // Pega o texto atual e aplica a substituição pretendida
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            StringBuilder sb = new StringBuilder(currentText);
            sb.replace(offset, offset + length, text);

            // Extrai apenas os números do resultado final
            String digits = sb.toString().replaceAll("\\D", "");

            if (digits.isEmpty()) {
                fb.replace(0, fb.getDocument().getLength(), FORMAT.format(0), null);
            } else {
                // Usa BigDecimal para evitar problemas de precisão do double
                BigDecimal value = new BigDecimal(digits).divide(new BigDecimal("100"));
                fb.replace(0, fb.getDocument().getLength(), FORMAT.format(value), null);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            replace(fb, offset, length, "", null);
        }
    }
}