package acal.com.acal_left.shared.model;

import lombok.Getter;

@Getter
public enum WaterParameterType {

    APPARENT_COLOR(1, "Cor aparente"),
    TURBIDITY(2, "Turbidez"),
    CHLORINE(3, "Cloro"),
    ESCHERICHIA_COLI(4, "Escherichia Coli"),
    TOTAL_COLIFORMS(5, "Coliformes Totais");

    private final int id;
    private final String description;

    WaterParameterType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static WaterParameterType fromId(int id) {
        for (WaterParameterType param : values()) {
            if (param.id == id) return param;
        }
        throw new IllegalArgumentException("ID de parâmetro inválido: " + id);
    }
}