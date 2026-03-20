package acal.com.acal_left.ui.flatlaf.screen.water.model.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AllWaterParameterTypesPresentValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllWaterParameterTypesPresent {

    String message() default "A análise deve conter exatamente um item para cada parâmetro de água";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

