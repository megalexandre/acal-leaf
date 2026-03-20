package acal.com.acal_left.ui.flatlaf.screen.water.model;

import acal.com.acal_left.core.model.WaterAnalysisItem;
import acal.com.acal_left.shared.model.WaterParameterType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWaterAnalysisItemForm {

    @NotNull(message = "O parametro de água é obrigatório")
    private WaterParameterType type;

    @NotNull(message = "Exigido é obrigatório")
    private String required;

    @NotNull(message = "Analisado é obrigatório")
    private String analyzed;

    @NotNull(message = "Conformidade é obrigatório")
    private String conformity;

    public WaterAnalysisItem toWaterAnalysisItem(LocalDate period) {
        return WaterAnalysisItem.builder()
                .type(type)
                .required(required)
                .analyzed(analyzed)
                .conformity(conformity)
                .period(period)
            .build();
    }

}
