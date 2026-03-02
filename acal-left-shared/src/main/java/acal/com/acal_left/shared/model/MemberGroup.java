package acal.com.acal_left.shared.model;

import lombok.Getter;

@Getter
public enum MemberGroup {

    FOUNDER(1, "Sócio Fundador"),
    EFFECTIVE(2, "Sócio Efetivo"),
    TEMPORARY(3, "Temporário");

    private final int databaseValue;
    private final String description;

    MemberGroup(int databaseValue, String description) {
        this.databaseValue = databaseValue;
        this.description = description;
    }

    public Integer getDatabaseValue() {
        return this.databaseValue;
    }

    public static MemberGroup fromDatabaseValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (MemberGroup group : MemberGroup.values()) {
            if (group.databaseValue == value) {
                return group;
            }
        }
        throw new IllegalArgumentException("Invalid database value for MemberGroup: " + value);
    }

}

