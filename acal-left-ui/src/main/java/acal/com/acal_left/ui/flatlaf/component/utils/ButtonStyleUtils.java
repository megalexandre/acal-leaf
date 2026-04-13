package acal.com.acal_left.ui.flatlaf.component.utils;

import acal.com.acal_left.ui.flatlaf.utils.UIManagerColor;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

public final class ButtonStyleUtils {

    private static final Dimension DEFAULT_BUTTON_SIZE = new Dimension(140, 36);
    private static final Insets DEFAULT_MARGIN = new Insets(8, 18, 8, 18);
    private static final int ARC = 8;

    private ButtonStyleUtils() {
    }

    public static void applyPrimary(JButton button) {
        apply(button, Variant.PRIMARY, DEFAULT_BUTTON_SIZE);
    }

    public static void applySecondary(JButton button) {
        apply(button, Variant.SECONDARY, DEFAULT_BUTTON_SIZE);
    }

    public static void applyPrimary(JButton button, Dimension size) {
        apply(button, Variant.PRIMARY, size);
    }

    public static void applySecondary(JButton button, Dimension size) {
        apply(button, Variant.SECONDARY, size);
    }

    private static void apply(JButton button, Variant variant, Dimension size) {
        button.setFont(AppFontUtils.font(Font.BOLD, 13f));
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(DEFAULT_MARGIN);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.putClientProperty("JButton.arc", ARC);

        if (variant == Variant.PRIMARY) {
            button.setBackground(UIManagerColor.PRIMARY);
            button.setForeground(UIManagerColor.LIGHT_LIGHTER);
            button.setBorder(new LineBorder(UIManagerColor.PRIMARY.darker(), 1, true));
        } else {
            button.setBackground(UIManagerColor.SECONDARY_LIGHT);
            button.setForeground(UIManagerColor.DARK);
            button.setBorder(new LineBorder(UIManagerColor.SECONDARY, 1, true));
        }
    }

    private enum Variant {
        PRIMARY,
        SECONDARY
    }
}
