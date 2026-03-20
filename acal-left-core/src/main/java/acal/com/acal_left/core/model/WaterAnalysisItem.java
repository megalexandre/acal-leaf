package acal.com.acal_left.core.model;

import acal.com.acal_left.shared.model.WaterParameterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterAnalysisItem {

    private Integer id;
    private WaterParameterType type;
    private String required;
    private String analyzed;
    private String conformity;
    private LocalDate period;

}
