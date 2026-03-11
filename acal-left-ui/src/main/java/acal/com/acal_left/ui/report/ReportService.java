package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.InvoiceReportOutput;
import net.sf.jasperreports.engine.JRException;
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
        if(templateStream == null) {
            throw new RuntimeException("invalid path");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo", getLogo());

        try {
            createReport("/reports/invoice_title.jrxml", parameters, "SUBREPORT_TITLE");
            createReport("/reports/invoice_detail.jrxml", parameters, "SUBREPORT_DETAIL");
            createReport("/reports/invoice_water.jrxml", parameters, "SUBREPORT_WATER");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao compilar subreports", e);
        }

        return new ReportManager().generatePdfReport(
                templateStream,
                invoices,
                parameters
        );
    }

    private static void createReport(String name, Map<String, Object> parameters, String SUBREPORT_TITLE) throws JRException {
        InputStream titleStream = ReportService.class.getResourceAsStream(name);
        if (titleStream != null) {
            JasperReport titleReport = JasperCompileManager.compileReport(titleStream);
            parameters.put(SUBREPORT_TITLE, titleReport);
        }
    }

    private BufferedImage getLogo() {
        try {
            return ImageIO.read(Objects.requireNonNull(ReportService.class.getResourceAsStream("/images/acal.jpg")));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

}
