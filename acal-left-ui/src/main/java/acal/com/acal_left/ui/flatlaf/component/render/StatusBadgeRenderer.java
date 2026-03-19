package acal.com.acal_left.ui.flatlaf.component.render;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.ui.flatlaf.utils.UIManagerColor;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class StatusBadgeRenderer extends DefaultTableCellRenderer {

    private boolean selected;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.selected = isSelected;

        if (value instanceof Invoice.Status status) {
            label.setText(status.getName());
            if (!isSelected) {
                applyBusinessStyle(label, status);
            }
        }

        return label;
    }

    private void applyBusinessStyle(JLabel label, Invoice.Status status) {
        switch (status) {
            case OPEN -> {
                label.setBackground(UIManagerColor.INFO_LIGHT);
                label.setForeground(UIManagerColor.INFO);
            }
            case OVERDUE -> {
                label.setBackground(UIManagerColor.DANGER_LIGHT);
                label.setForeground(UIManagerColor.DANGER);
            }
            case AT_RISK -> {
                label.setBackground(UIManagerColor.WARNING_LIGHT);
                label.setForeground(UIManagerColor.WARNING);
            }
            case PAID -> {
                label.setBackground(UIManagerColor.SUCCESS_LIGHT);
                label.setForeground(UIManagerColor.SUCCESS);
            }
            default -> {
                label.setBackground(UIManagerColor.SECONDARY_LIGHT);
                label.setForeground(UIManagerColor.SECONDARY);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (selected) {
            setOpaque(true);
            super.paintComponent(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(8, 4, getWidth() - 16, getHeight() - 8, 12, 12);
        setOpaque(false);
        super.paintComponent(g2);
        g2.dispose();
    }
}