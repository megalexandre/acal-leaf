package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.InvoiceReportOutput;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    public byte[] createReport(List<InvoiceReportOutput> invoices) {

        String templatePath = "/reports/invoice.jrxml";
        var templateStream = ReportService.class.getResourceAsStream(templatePath);

        if (templateStream == null) {
            throw new IllegalStateException("Template de relatório não encontrado no classpath: " + templatePath);
        }

        Map<String, Object> parameters = new HashMap<>();

        // Compilar e adicionar os subreports
        try {
            InputStream titleStream = ReportService.class.getResourceAsStream("/reports/invoice_title.jrxml");
            InputStream detailStream = ReportService.class.getResourceAsStream("/reports/invoice_detail.jrxml");

            if (titleStream != null) {
                JasperReport titleReport = JasperCompileManager.compileReport(titleStream);
                parameters.put("SUBREPORT_TITLE", titleReport);
            }

            if (detailStream != null) {
                JasperReport detailReport = JasperCompileManager.compileReport(detailStream);
                parameters.put("SUBREPORT_DETAIL", detailReport);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao compilar subreports", e);
        }

        // Tentar carregar o logo
        try {
            BufferedImage logo = ImageIO.read(ReportService.class.getResourceAsStream("/images/acal.jpg"));
            parameters.put("logo", logo);
        } catch (IOException | NullPointerException e) {
            // Se não encontrar o logo, continua sem ele
            parameters.put("logo", null);
        }

        return new ReportManager().generatePdfReport(
                templateStream,
                invoices,
                parameters
        );
    }

    public static JRDataSource buildInvoiceDetailDataSource(
            String partner,
            String partnerNumber,
            String id,
            String accountNumber,
            String payment,
            String totalAmount,
            String partnerCategory,
            String address,
            String excessLimit,
            String date,
            String excessValue
    ) {
        Map<String, Object> row = new HashMap<>();
        row.put("partnerName", partner);
        row.put("partnerNumber", partnerNumber);
        row.put("id", id);
        row.put("reference", accountNumber);
        row.put("number", partnerNumber);
        row.put("paidAt", "Pago".equals(payment) ? "PAGO" : null);
        row.put("paidAtLabel", "Pago".equals(payment) ? "PAGO" : "");
        row.put("total", totalAmount);
        row.put("category", partnerCategory);
        row.put("address", address);
        row.put("addressDetail", "");
        row.put("consumptionStart", "");
        row.put("consumptionEnd", "");
        row.put("freeTier", excessLimit);
        row.put("waterValue", "R$ 0,00");
        row.put("addressNumber", partnerNumber);
        row.put("currentDate", date);
        row.put("categoryValue", "R$ 0,00");
        row.put("waterTotalValue", "R$ 0,00");
        row.put("paidUsageValue", excessValue);
        return new JRMapCollectionDataSource(Collections.singletonList(row));
    }

}
