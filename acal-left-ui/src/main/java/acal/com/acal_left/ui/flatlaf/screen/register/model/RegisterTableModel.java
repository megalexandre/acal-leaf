package acal.com.acal_left.ui.flatlaf.screen.register.model;

import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RegisterTableModel extends AbstractTableModel {

    @Getter
    private List<RegisterTableContent> items = new ArrayList<>();
    private final String[] columns = RegisterColumns.getLabels();

    public void setList(List<RegisterTableContent> items) {
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
        RegisterTableContent c = items.get(rowIndex);
        RegisterColumns column = RegisterColumns.values()[columnIndex];

        return switch (column) {
            case RegisterColumns.PARTNER -> c.getPartner();
            case RegisterColumns.NUMBER -> c.getNumber();
            case RegisterColumns.PAYMENT_DATE -> c.getPaymentDate();
            case RegisterColumns.PAYMENT_METHOD -> c.getPaymentMethod();
            case RegisterColumns.AMOUNT -> c.getAmount();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

