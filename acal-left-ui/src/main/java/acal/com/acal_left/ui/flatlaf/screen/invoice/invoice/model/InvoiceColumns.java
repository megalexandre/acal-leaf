package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import lombok.Getter;

import static java.util.Arrays.stream;

@Getter
public enum InvoiceColumns {
    NUMBER("Número:"),
    PARTNER("Sócio:"),
    ADDRESS("Endereço:"),
    PAID("Pago:"),
    PERIOD("Competência:")
    ;

    private final String name;
    InvoiceColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return stream(InvoiceColumns.values())
                .map(InvoiceColumns::getName)
                .toArray(String[]::new);
    }
}