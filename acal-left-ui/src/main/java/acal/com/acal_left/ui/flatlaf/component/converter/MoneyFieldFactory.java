package acal.com.acal_left.ui.flatlaf.component.converter;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFieldFactory {

    public static void configure(JFormattedTextField field) {

        Locale brasil = Locale.of("pt", "BR");
        NumberFormat format = NumberFormat.getCurrencyInstance(brasil);

        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(BigDecimal.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(BigDecimal.ZERO);

        field.setFormatterFactory(new DefaultFormatterFactory(formatter));
        field.setColumns(10);
        field.setValue(BigDecimal.ZERO);
    }
}