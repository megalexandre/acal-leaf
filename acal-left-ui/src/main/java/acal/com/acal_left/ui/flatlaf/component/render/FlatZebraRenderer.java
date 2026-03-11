package acal.com.acal_left.ui.flatlaf.component.render;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class FlatZebraRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            Color bg = UIManager.getColor("Table.background");
            Color alternateColor = UIManager.getColor("Table.alternateRowColor");
            c.setBackground(row % 2 == 0 ? alternateColor : bg);
            c.setForeground(UIManager.getColor("Table.foreground"));
        } else {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }

        return c;
    }
}