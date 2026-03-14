package acal.com.acal_left.ui.flatlaf.screen.invoice.create.model;

import acal.com.acal_left.core.model.Invoice;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class InvoiceGenerateTableModel extends AbstractTableModel {

    @Getter
    public enum InvoiceColumns {
        PARTNER("Sócio:"),
        ADDRESS("Endereço:"),
        CATEGORY("Categoria:"),
        TOTAL("Valor Total:"),
        HYDROMETER_STARTS("Hidrometro Inicial:"),
        HYDROMETER_ENDS("Hidrometro Final:"),
        GENERATE("Gerar")
        ;

        private final String name;
        InvoiceColumns(String name) { this.name = name; }

        public static String[] getLabels() {
            return stream(values())
                    .map(InvoiceColumns::getName)
                    .toArray(String[]::new);
        }
    }

    @Getter
    private List<InvoiceGenerateTableContent> items = new ArrayList<>();
    private final String[] columns = InvoiceColumns.getLabels();

    public void setList(List<InvoiceGenerateTableContent> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public Invoice get(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < items.size()) {
            return items.get(rowIndex).getItem();
        }
        return null;
    }

    @Override
    public int getRowCount() { return items.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceGenerateTableContent c = items.get(rowIndex);
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        return switch (column) {
            case PARTNER -> c.getPartner();
            case ADDRESS -> c.getAddress();
            case CATEGORY -> c.getCategory();
            case TOTAL -> c.getTotal();
            case HYDROMETER_STARTS -> c.getHydrometerStart();
            case HYDROMETER_ENDS -> c.getHydrometerEnd();
            case GENERATE -> c.isGenerate();
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        InvoiceGenerateTableContent c = items.get(rowIndex);
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        if (column == InvoiceColumns.HYDROMETER_STARTS || column == InvoiceColumns.HYDROMETER_ENDS) {
            return c.isHasHydrometer();
        }

        return column == InvoiceColumns.GENERATE;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        InvoiceGenerateTableContent c = items.get(rowIndex);
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        Long parsed = null;
        if (value != null && !value.toString().isBlank()) {
            try { parsed = Long.parseLong(value.toString().trim()); } catch (NumberFormatException ignored) {}
        }

        switch (column) {
            case HYDROMETER_STARTS -> c.setHydrometerStart(parsed);
            case HYDROMETER_ENDS   -> c.setHydrometerEnd(parsed);
            case GENERATE          -> c.setGenerate(Boolean.TRUE.equals(value));
            default -> {}
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];
        return switch (column) {
            case HYDROMETER_STARTS, HYDROMETER_ENDS -> Long.class;
            case GENERATE -> Boolean.class;
            default -> String.class;
        };
    }

    public static TableCellEditor numericCellEditor() {
        JTextField field = new JTextField();
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        return new DefaultCellEditor(field) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JTextField tf = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                tf.setText(value == null ? "" : value.toString());
                return tf;
            }
        };
    }
}

