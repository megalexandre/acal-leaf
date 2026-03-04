package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Invoice;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PaginatedInvoiceTableModel extends AbstractTableModel {

    @Getter
    private List<InvoiceTableContent> allItems = new ArrayList<>();

    @Getter
    private List<InvoiceTableContent> pageItems = new ArrayList<>();

    @Getter
    private final PaginationModel pagination;

    private final String[] columns = InvoiceColumns.getLabels();

    public PaginatedInvoiceTableModel() {
        this.pagination = new PaginationModel();
    }

    public void setList(List<InvoiceTableContent> items) {
        this.allItems = items;
        this.pagination.setTotalItems(items.size());
        this.pagination.setCurrentPage(0);
        updatePageItems();
    }

    private void updatePageItems() {
        int startIdx = pagination.getStartIndex();
        int endIdx = pagination.getEndIndex();

        if (startIdx < allItems.size()) {
            pageItems = new ArrayList<>(allItems.subList(startIdx, endIdx));
        } else {
            pageItems = new ArrayList<>();
        }

        fireTableDataChanged();
    }

    public Invoice get(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < pageItems.size()) {
            return pageItems.get(rowIndex).getItem();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return pageItems.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceTableContent c = pageItems.get(rowIndex);
        InvoiceColumns column = InvoiceColumns.values()[columnIndex];

        return switch (column) {
            case InvoiceColumns.PAID -> c.getPaid();
            case InvoiceColumns.PARTNER -> c.getPartner();
            case InvoiceColumns.NUMBER -> c.getNumber();
            case InvoiceColumns.PERIOD -> c.getPeriod();
            case InvoiceColumns.ADDRESS -> c.getAddress();
        };
    }
}

