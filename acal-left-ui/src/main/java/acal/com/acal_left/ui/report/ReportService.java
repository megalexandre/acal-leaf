package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.InvoiceReportOutput;

import java.util.HashMap;
import java.util.List;

public class ReportService {

    public byte[] createReport(List<InvoiceReportOutput> invoices) {

        String templatePath = "/reports/invoice_report.jrxml";
        var templateStream = ReportService.class.getResourceAsStream(templatePath);

        if (templateStream == null) {
            throw new IllegalStateException("Template de relatório não encontrado no classpath: " + templatePath);
        }

        return new ReportManager().generatePdfReport(
                templateStream,
                invoices,
                new HashMap<>()
        );
    }

}
