package acal.com.acal_left.ui.flatlaf.screen.register.model;

import lombok.Getter;

@Getter
public enum RegisterColumns {
    PARTNER("Sócio:"),
    NUMBER("Número:"),
    PAYMENT_DATE("Data de Pagamento:"),
    PAYMENT_METHOD("Forma de Pagamento:"),
    AMOUNT("Valor:")
    ;

    private final String name;
    RegisterColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return java.util.Arrays.stream(RegisterColumns.values())
                .map(RegisterColumns::getName)
                .toArray(String[]::new);
    }
}