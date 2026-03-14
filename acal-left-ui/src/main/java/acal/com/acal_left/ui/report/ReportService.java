package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.InvoiceReportOutput;
import net.sf.jasperreports.engine.JasperReport;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReportService {

    private static final JasperReport REPORT_MAIN   = ReportLoader.load("/reports/invoice.jrxml");
    private static final JasperReport REPORT_TITLE  = ReportLoader.load("/reports/invoice_title.jrxml");
    private static final JasperReport REPORT_DETAIL = ReportLoader.load("/reports/invoice_detail.jrxml");
    private static final JasperReport REPORT_WATER  = ReportLoader.load("/reports/invoice_water.jrxml");
    private static final BufferedImage LOGO          = loadLogo();

    public byte[] createReport(List<InvoiceReportOutput> invoices) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo",             LOGO);
        parameters.put("SUBREPORT_TITLE",  REPORT_TITLE);
        parameters.put("SUBREPORT_DETAIL", REPORT_DETAIL);
        parameters.put("SUBREPORT_WATER",  REPORT_WATER);

        return new ReportManager().generatePdfReport(
                REPORT_MAIN,
                invoices,
                parameters
        );
    }

    private static BufferedImage loadLogo() {
        try {
            return ImageIO.read(Objects.requireNonNull(
                ReportService.class.getResourceAsStream("/images/acal.jpg")));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

}
