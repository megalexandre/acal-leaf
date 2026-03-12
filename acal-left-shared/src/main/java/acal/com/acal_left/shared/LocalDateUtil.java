package acal.com.acal_left.shared;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class LocalDateUtil {
    private static final DateTimeFormatter PERIOD_FORMATTER = ofPattern("MM/yyyy");
    private static final DateTimeFormatter LOCAL_TIME_FORMATTER = ofPattern("dd/MM/yyyy");

    public static String formatPeriod(LocalDate value) {
        if (value == null) {
            return "";
        }
        return value.format(PERIOD_FORMATTER);
    }

    public static LocalDate fromString(String value) {

        if (value == null) {
            return null;
        }

        return LocalDate.parse(value, LOCAL_TIME_FORMATTER);
    }


}
