package acal.com.acal_left.ui.flatlaf.screen.partner.model;

import acal.com.acal_left.core.model.Partner;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PartnerTableModel extends AbstractTableModel {

    private final String[] columns = PartnerColumns.getLabels();
    private List<PartnerTableContent> items = new ArrayList<>();

    public void setList(List<PartnerTableContent> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public Partner get(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < items.size()) {
            return items.get(rowIndex).getPartner();
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
        PartnerTableContent c = items.get(rowIndex);
        PartnerColumns column = PartnerColumns.values()[columnIndex];

        return switch (column) {
            case NAME -> c.getName();
            case DOCUMENT -> c.getDocument();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

