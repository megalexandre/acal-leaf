package acal.com.acal_left.ui.report;

import acal.com.acal_left.infrastructure.ReportManager;
import acal.com.acal_left.ui.report.out.InvoiceReportOutput;

import java.util.List;

public class ReportService {

    public byte[] createReport(List<InvoiceReportOutput> invoices) {
        var templateStream = getClass().getClassLoader()
                .getResourceAsStream("reports/invoice_report.jrxml");

        if (templateStream == null) {
            throw new IllegalStateException("Template de relatório não encontrado!");
        }

        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        java.util.Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("title", "Pesquisa de Contas");
        parameters.put("reportDate", java.time.LocalDateTime.now().format(formatter));
        parameters.put("totalRecords", invoices.size());

        ReportManager reportManager = new ReportManager();

        return reportManager.generatePdfReport(
                templateStream,
                invoices,
                parameters
        );
    }

}
