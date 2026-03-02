package acal.com.acal_left.resouces.repository.conveters;

import acal.com.acal_left.shared.model.MemberGroup;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GroupConverter implements AttributeConverter<MemberGroup, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MemberGroup group) {
        if (group == null) {
            return null;
        }
        return group.getDatabaseValue();
    }

    @Override
    public MemberGroup convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return MemberGroup.fromDatabaseValue(value);
    }
}

