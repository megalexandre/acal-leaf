package acal.com.acal_left.shared.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum GenerateInvoiceType {

    ALL(1, "Todas"),
    WITH_HYDROMETER(2, "Com hidrômetro"),
    WITHOUT_HYDROMETER(3, "Sem hidrômetro");

    private final int value;
    private final String description;

    private static final Map<Integer, GenerateInvoiceType> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(GenerateInvoiceType::getValue, Function.identity()));

    GenerateInvoiceType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static GenerateInvoiceType from(int value) {
        GenerateInvoiceType group = LOOKUP.get(value);
        if (group == null) {
            throw new IllegalArgumentException("Valor de banco de dados inválido: " + value);
        }
        return group;
    }

}

