package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.InvoiceReportOutput;

import java.util.HashMap;
import java.util.List;

public class ReportService {

    public byte[] createReport(List<InvoiceReportOutput> invoices) {

        var templateStream = getClass().getClassLoader()
                .getResourceAsStream("reports/invoice_report.jrxml");

        if (templateStream == null) {
            throw new IllegalStateException("Template de relatório não encontrado!");
        }

        return new ReportManager().generatePdfReport(
                templateStream,
                invoices,
                new HashMap<>()
        );
    }

}
