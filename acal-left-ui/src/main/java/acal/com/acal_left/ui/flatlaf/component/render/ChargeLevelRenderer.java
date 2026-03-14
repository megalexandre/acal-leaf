package acal.com.acal_left.ui.flatlaf.component.render;

import acal.com.acal_left.shared.model.ChargeLevel;
import acal.com.acal_left.ui.flatlaf.screen.charge.model.ChargeTableContent;
import acal.com.acal_left.ui.flatlaf.screen.charge.model.ChargeTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ChargeLevelRenderer extends DefaultTableCellRenderer {

    // Blends a theme color with white at the given opacity (0.0 = full white, 1.0 = full color)
    private Color tint(Color base, float alpha) {
        int r = (int) (base.getRed()   * alpha + 255 * (1 - alpha));
        int g = (int) (base.getGreen() * alpha + 255 * (1 - alpha));
        int b = (int) (base.getBlue()  * alpha + 255 * (1 - alpha));
        return new Color(r, g, b);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected && table.getModel() instanceof ChargeTableModel model) {
            int modelRow = table.convertRowIndexToModel(row);
            ChargeTableContent content = model.getItems().get(modelRow);

            // Zebra: even rows slightly more transparent than odd rows
            float alpha = (row % 2 == 0) ? 0.18f : 0.28f;

            Color base = content.getLevel() == ChargeLevel.OVERDUE_NOTICE
                    ? UIManager.getColor("Actions.Yellow")
                    : UIManager.getColor("Actions.Red");

            c.setBackground(tint(base,alpha ));
            c.setForeground(UIManager.getColor("Table.foreground"));
        } else if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }

        return c;
    }
}
