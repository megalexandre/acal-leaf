package acal.com.acal_left.ui.flatlaf.screen.link.model;

import acal.com.acal_left.core.model.Link;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import static acal.com.acal_left.ui.flatlaf.screen.partner.model.PartnerColumns.DOCUMENT;

public class LinkTableModel extends AbstractTableModel {

    private final String[] columns = LinkColumns.getLabels();
    private List<LinkTableContent> items = new ArrayList<>();

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
            case LinkColumns.ADDRESS -> c.getDocument();
            case LinkColumns.NUMBER -> c.getNumber();

        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

