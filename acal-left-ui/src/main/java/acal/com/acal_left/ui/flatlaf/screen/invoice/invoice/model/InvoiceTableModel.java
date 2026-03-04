package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Invoice;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTableModel extends AbstractTableModel {

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
            case InvoiceColumns.PAID -> c.getPaid();
            case InvoiceColumns.PARTNER -> c.getPartner();
            case InvoiceColumns.NUMBER -> c.getNumber();
            case InvoiceColumns.PERIOD -> c.getPeriod();
            case InvoiceColumns.ADDRESS -> c.getAddress();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

