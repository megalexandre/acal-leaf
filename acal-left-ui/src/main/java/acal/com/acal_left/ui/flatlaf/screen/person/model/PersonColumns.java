package acal.com.acal_left.ui.flatlaf.screen.person.model;

import lombok.Getter;

@Getter
public enum PersonColumns {
    NAME("Nome:"),
    DOCUMENT("Documento:");

    private final String name;
    PersonColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return java.util.Arrays.stream(PersonColumns.values())
                .map(PersonColumns::getName)
                .toArray(String[]::new);
    }
}