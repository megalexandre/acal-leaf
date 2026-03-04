package acal.com.acal_left.ui.flatlaf.screen.link.model;

import lombok.Getter;

@Getter
public enum LinkColumns {
    PARTNER("Sócio:"),
    ADDRESS("Endereço:"),
    ACTIVE("Ativo:")
    ;

    private final String name;
    LinkColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return java.util.Arrays.stream(LinkColumns.values())
                .map(LinkColumns::getName)
                .toArray(String[]::new);
    }
}