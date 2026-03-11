package acal.com.acal_left.shared;

import java.text.NumberFormat;
import java.util.Locale;

public class DoubleUtil {

    private static final Locale PT_BR = Locale.of("pt", "BR");
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(PT_BR);

    static {
        NUMBER_FORMAT.setMinimumFractionDigits(0);
        NUMBER_FORMAT.setMaximumFractionDigits(0);
        NUMBER_FORMAT.setGroupingUsed(true);
    }

    public static String format(Double value) {
        if (value == null) return "0";
        return NUMBER_FORMAT.format(value);
    }

}


