package acal.com.acal_left.ui.flatlaf.screen.water.model.constraint;

import acal.com.acal_left.shared.model.WaterParameterType;
import acal.com.acal_left.ui.flatlaf.screen.water.model.CreateWaterAnalysisItemForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AllWaterParameterTypesPresentValidator
        implements ConstraintValidator<AllWaterParameterTypesPresent, List<CreateWaterAnalysisItemForm>> {


    @Override
    public boolean isValid(List<CreateWaterAnalysisItemForm> items, ConstraintValidatorContext context) {
        if (items == null || items.isEmpty()) {
            return false;
        }

        Set<WaterParameterType> presentTypes = items.stream()
                .filter(item -> item != null && item.getType() != null)
                .map(CreateWaterAnalysisItemForm::getType)
                .collect(Collectors.toSet());

        Set<WaterParameterType> missingTypes = Arrays.stream(WaterParameterType.values())
                .filter(type -> !presentTypes.contains(type))
                .collect(Collectors.toSet());

        if (!missingTypes.isEmpty()) {
            String missing = missingTypes.stream()
                    .map(WaterParameterType::getDescription)
                    .collect(Collectors.joining(", "));

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Parâmetros ausentes na análise: " + missing
            ).addConstraintViolation();

            return false;
        }

        return true;
    }
}

