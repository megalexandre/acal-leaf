package acal.com.acal_left.ui.flatlaf.screen.charge.model;

import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class ChargeTableModel extends AbstractTableModel {

    @Getter
    public enum InvoiceColumns {
        PARTNER("Sócio:"),
        ADDRESS("Endereço:"),
        NUMBER_OF_INVOICES("Número de Faturas:"),
        TOTAL("Valor Total:"),
        STATUS("Status:"),
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
    private List<ChargeTableContent> items = new ArrayList<>();
    private final String[] columns = InvoiceColumns.getLabels();

    public void setList(List<ChargeTableContent> items) {
        this.items = items;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() { return items.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ChargeTableContent c = items.get(rowIndex);
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        return switch (column) {
            case STATUS -> c.getStatus();
            case PARTNER -> c.getPartner();
            case TOTAL -> c.getTotal();
            case ADDRESS -> c.getAddress();
            case NUMBER_OF_INVOICES ->  c.getNumberOfInvoices();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        return switch (column) {
            case NUMBER_OF_INVOICES -> Long.class;
            default -> String.class;
        };
    }
}

