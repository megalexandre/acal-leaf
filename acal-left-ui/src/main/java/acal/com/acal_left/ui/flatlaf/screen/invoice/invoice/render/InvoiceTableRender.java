package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.render;

import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceColumns;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class InvoiceTableRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            InvoiceTableModel model = (InvoiceTableModel) table.getModel();
            int modelRow = table.convertRowIndexToModel(row);

            // Verificar se a coluna PAID da linha atual é "Sim"
            Object paidValue = model.getValueAt(modelRow, InvoiceColumns.PAID.ordinal());
            boolean isPaid = "Sim".equals(paidValue);

            if (isPaid) {
                // Linha toda verde quando pago
                Color lightGreen = new Color(144, 238, 144); // Light green RGB
                c.setBackground(lightGreen);
                c.setForeground(UIManager.getColor("Table.foreground"));
            } else {
                // Cores normais alternadas quando não pago
                Color bg = UIManager.getColor("Table.background");
                Color alternateColor = UIManager.getColor("Table.alternateRowColor");
                c.setBackground(row % 2 == 0 ? alternateColor : bg);
                c.setForeground(UIManager.getColor("Table.foreground"));
            }
        } else {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }

        ((JLabel) c).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        return c;
    }
}


