package acal.com.acal_left.ui.flatlaf.component.utils;

import acal.com.acal_left.ui.flatlaf.component.filter.NumericDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

public class SwingUtils {

    public static void applyNumericFilter(JTextField textField) {
        if (textField.getDocument() instanceof AbstractDocument doc) {
            doc.setDocumentFilter(new NumericDocumentFilter());
        }
    }
}