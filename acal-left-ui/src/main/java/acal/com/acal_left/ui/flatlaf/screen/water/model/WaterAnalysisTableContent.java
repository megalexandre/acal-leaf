package acal.com.acal_left.ui.flatlaf.screen.water.model;

import acal.com.acal_left.core.model.WaterAnalysisItem;
import acal.com.acal_left.shared.LocalDateUtil;
import lombok.Data;

@Data
public class WaterAnalysisTableContent {

    private final String period;
    private final String type;
    private final String required;
    private final String analyzed;
    private final String conformity;

    public WaterAnalysisTableContent(WaterAnalysisItem item) {
        this.period = LocalDateUtil.formatPeriod(item.getPeriod());
        this.type = item.getType().getDescription();
        this.required = item.getRequired();
        this.analyzed = item.getAnalyzed();
        this.conformity = item.getConformity();
    }

}