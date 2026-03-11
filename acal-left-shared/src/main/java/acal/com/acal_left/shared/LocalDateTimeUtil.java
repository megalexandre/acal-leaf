package acal.com.acal_left.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class LocalDateTimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = ofPattern("dd/MM/yyyy");

    public static String formatDateTime(LocalDateTime value) {
        if (value == null) {
            return "";
        }

        return value.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime fromString(String value) {

        if (value == null) {
            return null;
        }

        return LocalDate.parse(value, DATE_TIME_FORMATTER).atTime(LocalTime.now());
    }



}
