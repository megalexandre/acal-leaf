package acal.com.acal_left.ui.flatlaf.screen.address.model;

import lombok.Getter;

@Getter
public enum AddressColumns {
    NAME("Nome:");

    private final String name;
    AddressColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return java.util.Arrays.stream(AddressColumns.values())
                .map(AddressColumns::getName)
                .toArray(String[]::new);
    }
}