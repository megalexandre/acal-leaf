package acal.com.acal_left.ui.flatlaf.screen.link.render;

import acal.com.acal_left.ui.flatlaf.screen.link.model.LinkTableModel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon; // Opcional, se usar ícones
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class LinkTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int modelRow = table.convertRowIndexToModel(row);
        LinkTableModel model = (LinkTableModel) table.getModel();
        boolean active = model.getItems().get(modelRow).getItem().getActive();

        if (!isSelected) {
            if (!active) {
                c.setBackground(UIManager.getColor("Table.alternateRowColor"));
                c.setForeground(UIManager.getColor("Label.disabledForeground"));
                c.setFont(c.getFont().deriveFont(Font.ITALIC));
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
                c.setFont(table.getFont());
            }
        }

        ((JLabel)c).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        return c;
    }
}