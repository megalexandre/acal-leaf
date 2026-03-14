package acal.com.acal_left.ui.report.out;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

public class ChargeReportDataSource {

    public static JRDataSource build(ChargeReportOutput data) {
        return new JRBeanCollectionDataSource(List.of(data));
    }
}

