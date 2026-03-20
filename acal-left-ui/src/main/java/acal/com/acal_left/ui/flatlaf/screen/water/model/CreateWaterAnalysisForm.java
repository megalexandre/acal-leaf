package acal.com.acal_left.ui.flatlaf.screen.water.model;

import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.ui.flatlaf.screen.water.model.constraint.AllWaterParameterTypesPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWaterAnalysisForm {

    @AllWaterParameterTypesPresent
    private List<CreateWaterAnalysisItemForm> analysis;

    @NotNull(message = "O parametro de água é obrigatório")
    private LocalDate period;

    public WaterAnalysis toWaterAnalysis() {
        return WaterAnalysis.builder()
                .analysis(
                    analysis.stream()
                    .map(it -> it.toWaterAnalysisItem(period))
                    .toList()
                ).build();
    }

}
