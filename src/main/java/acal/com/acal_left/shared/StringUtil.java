package acal.com.acal_left.shared;

import java.util.Optional;

public class StringUtil {

    public static String safe(String value) {
        return Optional.ofNullable(value)
                .map(it -> it.trim().toLowerCase())
                .orElse("");
    }
}
