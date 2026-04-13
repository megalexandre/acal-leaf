package acal.com.acal_left.ui.flatlaf.screen.category.render;

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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Set;

public class CategoryTableRenderer extends DefaultTableCellRenderer {

    private final int badgeColumn;
    private final Set<Integer> moneyColumns;
    private boolean selected;
    private boolean badgeCell;

    public CategoryTableRenderer(int badgeColumn, Set<Integer> moneyColumns) {
        this.badgeColumn = badgeColumn;
        this.moneyColumns = moneyColumns;
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

        selected = isSelected;
        badgeCell = column == badgeColumn;

        applyRowStyle(table, label, row, isSelected);
        applyAlignment(label, column);

        if (badgeCell) {
            applyYesNoBadgeStyle(label, value, isSelected);
        } else {
            label.setOpaque(true);
            label.setFont(label.getFont().deriveFont(Font.PLAIN));
        }

        return label;
    }

    private void applyRowStyle(JTable table, JLabel label, int row, boolean isSelected) {
        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
            return;
        }

        Color rowColor = row % 2 == 0
                ? resolveColor("Table.alternateRowColor", UIManagerColor.LIGHT)
                : resolveColor("Table.background", UIManagerColor.LIGHT_LIGHTER);

        label.setBackground(rowColor);
        label.setForeground(resolveColor("Table.foreground", UIManagerColor.DARK));
    }

    private void applyAlignment(JLabel label, int column) {
        if (moneyColumns.contains(column)) {
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            return;
        }

        if (column == badgeColumn) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return;
        }

        label.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void applyYesNoBadgeStyle(JLabel label, Object value, boolean isSelected) {
        String raw = value == null ? "" : value.toString().trim();
        boolean yes = "Sim".equalsIgnoreCase(raw);

        label.setText(yes ? "SIM" : "NAO");

        if (isSelected) {
            label.setOpaque(true);
            return;
        }

        label.setOpaque(false);
        label.setBackground(yes ? UIManagerColor.SUCCESS_LIGHT : UIManagerColor.DANGER_LIGHT);
        label.setForeground(yes ? UIManagerColor.SUCCESS : UIManagerColor.DANGER);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
    }

    private Color resolveColor(String key, Color fallback) {
        Color color = UIManager.getColor(key);
        return color != null ? color : fallback;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (selected || !badgeCell) {
            super.paintComponent(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(10, 6, getWidth() - 20, getHeight() - 12, 14, 14);
        super.paintComponent(g2);
        g2.dispose();
    }
}

