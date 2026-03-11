package acal.com.acal_left.ui.report.out;


import acal.com.acal_left.core.model.WaterAnalysisItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceReportWaterParamOutput {

    private String name;
    private String required;
    private String analyzed;
    private String conformity;

    public InvoiceReportWaterParamOutput(WaterAnalysisItem waterAnalysisItem){
        this.name = waterAnalysisItem.getType().getDescription();
        this.required = waterAnalysisItem.getRequired();
        this.analyzed = waterAnalysisItem.getAnalyzed();
        this.conformity = waterAnalysisItem.getConformity();
    }

}

