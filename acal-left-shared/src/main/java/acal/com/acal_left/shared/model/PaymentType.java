package acal.com.acal_left.shared.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum PaymentType {

    MONEY(1, "Dinheiro"),
    PIX(2, "Pix");

    private final int value;
    private final String description;

    private static final Map<Integer, PaymentType> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(PaymentType::getValue, Function.identity()));

    PaymentType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static PaymentType from(int value) {
        PaymentType group = LOOKUP.get(value);
        if (group == null) {
            throw new IllegalArgumentException("Invalid:" + value);
        }
        return group;
    }

}

