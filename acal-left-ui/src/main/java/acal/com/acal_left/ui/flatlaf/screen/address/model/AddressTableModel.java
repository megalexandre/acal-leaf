package acal.com.acal_left.ui.flatlaf.screen.address.model;

import acal.com.acal_left.core.model.Address;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AddressTableModel extends AbstractTableModel {

    private final String[] columns = AddressColumns.getLabels();
    private List<AddressTableContent> items = new ArrayList<>();

    public void setList(List<AddressTableContent> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public Address get(int rowIndex) {
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
        AddressTableContent c = items.get(rowIndex);
        AddressColumns column = AddressColumns.values()[columnIndex];

        return switch (column) {
            case NAME -> c.getLabel();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

