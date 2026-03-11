package acal.com.acal_left.ui.flatlaf.screen.person.model;

import acal.com.acal_left.core.model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private final String[] columns = PersonColumns.getLabels();
    private List<PersonTableContent> items = new ArrayList<>();

    public void setList(List<PersonTableContent> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public Person get(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < items.size()) {
            return items.get(rowIndex).getPerson();
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
        PersonTableContent c = items.get(rowIndex);
        PersonColumns column = PersonColumns.values()[columnIndex];

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

