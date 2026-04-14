package acal.com.acal_left.ui.flatlaf.component.filter;

import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 * TextField com máscara automática para CPF/CNPJ.
 * CPF: 000.000.000-00
 * CNPJ: 00.000.000/0000-00
 */
public class CpfCnpjTextField extends JTextField {

    public CpfCnpjTextField() {
        super();
        setDocument(new PlainDocument());
        getDocument().putProperty("filterBypass", null);
        ((PlainDocument) getDocument()).setDocumentFilter(new CpfCnpjDocumentFilter());
    }

    /**
     * Retorna o texto sem formatação (apenas dígitos).
     */
    public String getUnformattedValue() {
        return getText().replaceAll("[^0-9]", "");
    }

    /**
     * Define o texto formatando automaticamente.
     */
    @Override
    public void setText(String text) {
        if (text == null || text.isEmpty()) {
            super.setText("");
            return;
        }

        String digits = text.replaceAll("[^0-9]", "");
        String formatted;
        if (digits.length() <= 11) {
            formatted = formatCpf(digits);
        } else {
            formatted = formatCnpj(digits.substring(0, Math.min(digits.length(), 14)));
        }

        // Evita o bloqueio do DocumentFilter em set programático com pontuação.
        PlainDocument document = (PlainDocument) getDocument();
        DocumentFilter currentFilter = document.getDocumentFilter();
        document.setDocumentFilter(null);
        try {
            super.setText(formatted);
        } finally {
            document.setDocumentFilter(currentFilter);
        }
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
}
