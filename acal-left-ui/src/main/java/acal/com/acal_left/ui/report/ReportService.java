package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.InvoiceReportOutput;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            if (titleStream != null) {
                JasperReport titleReport = JasperCompileManager.compileReport(titleStream);
                parameters.put("SUBREPORT_TITLE", titleReport);
            }

            InputStream detailStream = ReportService.class.getResourceAsStream("/reports/invoice_detail.jrxml");
            if (detailStream != null) {
                JasperReport detailReport = JasperCompileManager.compileReport(detailStream);
                parameters.put("SUBREPORT_DETAIL", detailReport);
            }

            /*

            InputStream waterStream = ReportService.class.getResourceAsStream("/reports/invoice_water.jrxml");




            if (waterStream != null) {
                JasperReport waterReport = JasperCompileManager.compileReport(waterStream);
                parameters.put("SUBREPORT_WATER", waterReport);
            }
            */
        } catch (Exception e) {
            throw new RuntimeException("Erro ao compilar subreports", e);
        }

        try {
            BufferedImage logo = ImageIO.read(Objects.requireNonNull(ReportService.class.getResourceAsStream("/images/acal.jpg")));
            parameters.put("logo", logo);
        } catch (IOException | NullPointerException e) {
            parameters.put("logo", null);
        }

        return new ReportManager().generatePdfReport(
                templateStream,
                invoices,
                parameters
        );
    }

}
