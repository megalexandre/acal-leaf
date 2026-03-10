package acal.com.acal_left.shared;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class BigDecimalUtil {


    private static final Locale PT_BR = Locale.of("pt", "BR");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(PT_BR);
    private static final NumberFormat DECIMAL_FORMAT = NumberFormat.getNumberInstance(PT_BR);

    static {
        DECIMAL_FORMAT.setMinimumFractionDigits(2);
        DECIMAL_FORMAT.setMaximumFractionDigits(2);
    }

    public static String toBRL(BigDecimal value) {
        if (value == null) return "R$ 0,00";
        return CURRENCY_FORMAT.format(value);
    }
    public static String formatPlain(BigDecimal value) {
        if (value == null) return "0,00";
        return DECIMAL_FORMAT.format(value);
    }
}