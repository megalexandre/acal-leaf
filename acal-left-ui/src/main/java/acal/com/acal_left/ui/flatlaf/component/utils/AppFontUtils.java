package acal.com.acal_left.ui.flatlaf.component.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class AppFontUtils {

    // Free fonts with visual feel close to Amazon Ember.
    private static final String[] PREFERRED_FAMILIES = {
            "Manrope",
            "Inter",
            "Source Sans 3",
            "Noto Sans",
            Font.SANS_SERIF
    };

    private static final Set<String> INSTALLED_FAMILIES = new HashSet<>(
            Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
    );

    private AppFontUtils() {
    }

    public static Font font(int style, float size) {
        int safeStyle = normalizeStyle(style);
        int safeSize = Math.max(10, Math.round(size));

        for (String family : PREFERRED_FAMILIES) {
            if (INSTALLED_FAMILIES.contains(family)) {
                return new Font(family, safeStyle, safeSize);
            }
        }

        return new Font(Font.SANS_SERIF, safeStyle, safeSize);
    }

    private static int normalizeStyle(int style) {
        int allowedBits = Font.PLAIN | Font.BOLD | Font.ITALIC;
        return style & allowedBits;
    }
}

