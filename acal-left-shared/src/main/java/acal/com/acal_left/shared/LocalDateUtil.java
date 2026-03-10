package acal.com.acal_left.shared;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class LocalDateUtil {
    private static final DateTimeFormatter PERIOD_FORMATTER = ofPattern("MM/yyyy");

    public static String formatPeriod(LocalDate value) {
        if (value == null) {
            return "";
        }
        return value.format(PERIOD_FORMATTER);
    }
}
