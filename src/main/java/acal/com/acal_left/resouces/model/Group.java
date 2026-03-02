package acal.com.acal_left.resouces.model;

import lombok.Getter;

@Getter
public enum Group {

    SOCIO_FUNDADOR("Sócio Fundador"),
    SOCIO_EFETIVO("Sócio Efetivo"),
    TEMPORARIO("Temporário");

    private final String description;

    Group(String description) {
        this.description = description;
    }

    public int getDatabaseValue() {
        return this.ordinal() + 1;
    }

    public static Group fromDatabaseValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (Group group : Group.values()) {
            if (group.getDatabaseValue() == value) {
                return group;
            }
        }
        throw new IllegalArgumentException("Invalid group value: " + value);
    }
}

