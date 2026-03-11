package acal.com.acal_left.ui.report.out;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

public class InvoiceReportDataSource {

    public static JRDataSource build(InvoiceReportOutput data) {
        return new JRBeanCollectionDataSource(List.of(data));
    }

    public static JRDataSource buildWaterParams(InvoiceReportOutput invoiceReportOutput) {
        return new JRBeanCollectionDataSource(invoiceReportOutput.getWaterParams());
    }
}
