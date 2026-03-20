package acal.com.acal_left.ui.flatlaf.screen.water.model;

import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class WaterAnalysisTableModel extends AbstractTableModel {

    @Getter
    private List<WaterAnalysisTableContent> items = new ArrayList<>();
    private final String[] columns = WaterAnalysisColumns.getLabels();

    public void setList(List<WaterAnalysisTableContent> items) {
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
        WaterAnalysisTableContent c = items.get(rowIndex);
        WaterAnalysisColumns column = WaterAnalysisColumns.values()[columnIndex];

        return switch (column) {
            case WaterAnalysisColumns.PERIOD -> c.getPeriod();
            case WaterAnalysisColumns.TYPE -> c.getType();
            case WaterAnalysisColumns.REQUIRED -> c.getRequired();
            case WaterAnalysisColumns.ANALYZED -> c.getAnalyzed();
            case WaterAnalysisColumns.CONFORMITY -> c.getConformity();
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

