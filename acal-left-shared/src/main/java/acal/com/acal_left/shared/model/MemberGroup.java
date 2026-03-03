package acal.com.acal_left.shared.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum MemberGroup {

    FOUNDER(1, "Sócio Fundador"),
    EFFECTIVE(2, "Sócio Efetivo"),
    TEMPORARY(3, "Temporário");

    private final int value;
    private final String description;

    private static final Map<Integer, MemberGroup> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(MemberGroup::getValue, Function.identity()));

    MemberGroup(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static MemberGroup from(int value) {
        MemberGroup group = LOOKUP.get(value);
        if (group == null) {
            throw new IllegalArgumentException("Valor de banco de dados inválido: " + value);
        }
        return group;
    }

}

