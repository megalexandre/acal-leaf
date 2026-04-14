package acal.com.acal_left.ui.flatlaf.screen.address.render;

import acal.com.acal_left.ui.flatlaf.utils.UIManagerColor;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class AddressTableRenderer extends DefaultTableCellRenderer {

    public AddressTableRenderer() {
        setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        label.setOpaque(true);

        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        } else {
            Color rowColor = row % 2 == 0
                    ? resolveColor("Table.alternateRowColor", UIManagerColor.LIGHT)
                    : resolveColor("Table.background", UIManagerColor.LIGHT_LIGHTER);
            label.setBackground(rowColor);
            label.setForeground(resolveColor("Table.foreground", UIManagerColor.DARK));
        }

        return label;
    }

    private Color resolveColor(String key, Color fallback) {
        Color color = UIManager.getColor(key);
        return color != null ? color : fallback;
    }
}

