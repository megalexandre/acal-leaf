package acal.com.acal_left.ui.flatlaf.component.filter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Campo de texto com máscara MM/AAAA.
 * Notifica listeners quando o valor se torna válido ou inválido.
 */
public class MonthYearField extends JFormattedTextField {

    private static final String MASK       = "##/####";
    private static final char PLACEHOLDER = '_';

    private final List<Runnable> changeListeners = new ArrayList<>();

    public MonthYearField() {
        super(createFormatter());
        setColumns(7);
        setFocusLostBehavior(JFormattedTextField.COMMIT);

        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { notifyListeners(); }
            public void removeUpdate(DocumentEvent e)  { notifyListeners(); }
            public void changedUpdate(DocumentEvent e) { notifyListeners(); }
        });
    }

    private static MaskFormatter createFormatter() {
        try {
            MaskFormatter fmt = new MaskFormatter(MASK);
            fmt.setPlaceholderCharacter(PLACEHOLDER);
            fmt.setValidCharacters("0123456789");
            fmt.setAllowsInvalid(false);
            fmt.setOverwriteMode(true);
            return fmt;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /** Retorna true somente quando MM/AAAA está completamente preenchido e é uma data válida. */
    public boolean isComplete() {
        String raw = getText();
        if (raw == null || raw.contains(String.valueOf(PLACEHOLDER))) return false;
        return parseYearMonth() != null;
    }

    /**
     * Retorna o {@link YearMonth} digitado, ou {@code null} se inválido/incompleto.
     */
    public YearMonth getYearMonth() {
        return parseYearMonth();
    }

    private YearMonth parseYearMonth() {
        String raw = getText();
        if (raw == null) return null;
        raw = raw.replace(String.valueOf(PLACEHOLDER), "").trim();
        // esperado MM/YYYY
        String[] parts = raw.split("/");
        if (parts.length != 2) return null;
        try {
            int month = Integer.parseInt(parts[0]);
            int year  = Integer.parseInt(parts[1]);
            if (month < 1 || month > 12 || year < 1) return null;
            return YearMonth.of(year, month);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Registra um listener chamado sempre que o conteúdo muda. */
    public void addChangeListener(Runnable listener) {
        changeListeners.add(listener);
    }

    private void notifyListeners() {
        changeListeners.forEach(Runnable::run);
    }
}

