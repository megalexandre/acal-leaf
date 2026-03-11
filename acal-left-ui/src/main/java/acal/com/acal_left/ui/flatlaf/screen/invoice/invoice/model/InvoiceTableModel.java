package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Invoice;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class InvoiceTableModel extends AbstractTableModel {

    @Getter
    public enum InvoiceColumns {
        NUMBER("Número:"),
        PARTNER("Sócio:"),
        ADDRESS("Endereço:"),
        PERIOD("Competência:"),
        TOTAL("Valor Total:"),
        DUE_DATE("Vencimento:"),
        STATUS("Status:")
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
    private List<InvoiceTableContent> items = new ArrayList<>();
    private final String[] columns = InvoiceColumns.getLabels();

    public void setList(List<InvoiceTableContent> items) {
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
        InvoiceTableContent c = items.get(rowIndex);
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        return switch (column) {
            case STATUS -> c.getStatus();
            case DUE_DATE -> c.getDueDate();
            case PARTNER -> c.getPartner();
            case TOTAL -> c.getTotal();
            case NUMBER -> c.getNumber();
            case PERIOD -> c.getPeriod();
            case ADDRESS -> c.getAddress();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        if (column == InvoiceColumns.STATUS) {
            return Invoice.Status.class;
        }

        return String.class;
    }
}

