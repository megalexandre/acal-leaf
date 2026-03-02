package acal.com.acal_left.resouces.conveters;

import acal.com.acal_left.resouces.model.Group;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GroupConverter implements AttributeConverter<Group, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Group group) {
        if (group == null) {
            return null;
        }
        return group.getDatabaseValue();
    }

    @Override
    public Group convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return Group.fromDatabaseValue(value);
    }
}

