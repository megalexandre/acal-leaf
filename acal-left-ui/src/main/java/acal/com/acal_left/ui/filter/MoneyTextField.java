package acal.com.acal_left.ui.filter;

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
        
        // Instala o filtro de digitação que fizemos antes
        ((AbstractDocument) getDocument()).setDocumentFilter(new MoneyDocumentFilter());
    }

    // --- O "DE/PARA" DIRETO NO COMPONENTE ---

    /**
     * Devolve o valor como BigDecimal pronto para sua entidade Category
     */
    public BigDecimal getBigDecimal() {
        try {
            String cleanString = getText().replaceAll("[R$\\s.]", "").replace(",", ".");
            // Como o filtro trabalha com centavos (ex: 125 vira R$ 1,25), 
            // dividimos por 100 para ter o valor real.
            return new BigDecimal(cleanString).divide(new BigDecimal("100"));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Recebe um BigDecimal da sua entidade e já formata na tela
     */
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
            // Garante que ao apagar tudo, o campo volte para R$ 0,00
            replace(fb, offset, length, "", null);
        }
    }
}