package acal.com.acal_left.ui.flatlaf.screen.partner.model;

import lombok.Getter;

@Getter
public enum PartnerColumns {
    NAME("Nome:"),
    DOCUMENT("Documento:");

    private final String name;
    PartnerColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return java.util.Arrays.stream(PartnerColumns.values())
                .map(PartnerColumns::getName)
                .toArray(String[]::new);
    }
}