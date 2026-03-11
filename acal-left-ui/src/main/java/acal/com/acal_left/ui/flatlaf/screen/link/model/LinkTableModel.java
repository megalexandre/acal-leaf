package acal.com.acal_left.ui.flatlaf.screen.link.model;

import acal.com.acal_left.core.model.Link;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class LinkTableModel extends AbstractTableModel {

    @Getter
    private List<LinkTableContent> items = new ArrayList<>();
    private final String[] columns = LinkColumns.getLabels();

    public void setList(List<LinkTableContent> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public Link get(int rowIndex) {
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
        LinkTableContent c = items.get(rowIndex);
        LinkColumns column = LinkColumns.values()[columnIndex];

        return switch (column) {
            case LinkColumns.PARTNER -> c.getName();
            case LinkColumns.ADDRESS -> c.getAddress();
            case LinkColumns.AMOUNT ->  c.getAmount();
            case LinkColumns.CATEGORY -> c.getCategory();
            case LinkColumns.ACTIVE -> c.getActive();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

