package acal.com.acal_left.ui.flatlaf.component.filter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Campo de texto com máscara DD/MM/AAAA.
 * Notifica listeners quando o valor se torna válido ou inválido.
 */
public class LocalDateField extends JFormattedTextField {

    private static final String MASK        = "##/##/####";
    private static final char   PLACEHOLDER = '_';

    private final List<Runnable> changeListeners = new ArrayList<>();

    public LocalDateField() {
        super(createFormatter());
        setColumns(10);

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

    /** Retorna true somente quando DD/MM/AAAA está completamente preenchido e é uma data válida. */
    public boolean isComplete() {
        String raw = getText();
        if (raw == null || raw.contains(String.valueOf(PLACEHOLDER))) return false;
        return parseLocalDate() != null;
    }

    /**
     * Retorna o {@link LocalDate} digitado, ou {@code null} se inválido/incompleto.
     */
    public LocalDate getLocalDate() {
        return parseLocalDate();
    }

    /**
     * Define o valor do campo a partir de um {@link LocalDate}.
     */
    public void setLocalDate(LocalDate date) {
        if (date == null) {
            setValue(null);
            return;
        }
        String formatted = String.format("%02d/%02d/%04d",
                date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        setText(formatted);
    }

    private LocalDate parseLocalDate() {
        String raw = getText();
        if (raw == null) return null;
        raw = raw.replace(String.valueOf(PLACEHOLDER), "").trim();
        // esperado DD/MM/YYYY
        String[] parts = raw.split("/");
        if (parts.length != 3) return null;
        try {
            int day   = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year  = Integer.parseInt(parts[2]);
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
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

