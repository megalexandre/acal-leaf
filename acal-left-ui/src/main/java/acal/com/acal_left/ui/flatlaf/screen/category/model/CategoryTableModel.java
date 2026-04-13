package acal.com.acal_left.ui.flatlaf.screen.category.model;

import acal.com.acal_left.core.model.Category;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {

    private final String[] columns = {"Categoria:", "Nome:", "Hidrômetro?", "Valor Água:", "Valor Sócio:", "Total:"};
    private List<CategoryTableContent> categories = new ArrayList<>();

    public void setList(List<CategoryTableContent> categories) {
        this.categories = categories;
        fireTableDataChanged();
    }

    public Category getCategory(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < categories.size()) {
            return categories.get(rowIndex).getCategory();
        }
        return null;
    }

    @Override
    public int getRowCount() { return categories.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CategoryTableContent c = categories.get(rowIndex);
        CategoryColumns column = CategoryColumns.values()[columnIndex];

        return switch (column) {
            case CATEGORY -> c.getMemberGroup();
            case NAME -> c.getName();
            case WATER -> c.getAmountWater();
            case PARTNER -> c.getAmountPartner();
            case TOTAL -> c.getTotal();
            case HYDROMETER -> c.getCategory().getIsHydrometer() ? "Sim" : "Não";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

