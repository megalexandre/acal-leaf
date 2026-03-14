package acal.com.acal_left.shared.model;

public enum ChargeLevel {
    OVERDUE_NOTICE("Cobrança"),
    DEACTIVATION_NOTICE("Corte");

    public final String description;

    ChargeLevel(String description) {
        this.description = description;
    }
}
