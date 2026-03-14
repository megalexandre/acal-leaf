package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.ChargeReportOutput;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChargeReportService {

    private static final JasperReport REPORT_MAIN     = ReportLoader.load("/reports/charge.jrxml");
    private static final JasperReport REPORT_TITLE    = ReportLoader.load("/reports/charge_title.jrxml");
    private static final JasperReport REPORT_DETAIL   = ReportLoader.load("/reports/charge_detail.jrxml");
    private static final JasperReport REPORT_INVOICES = ReportLoader.load("/reports/charge_invoices.jrxml");
    private static final BufferedImage LOGO            = loadLogo();

    public byte[] createReport(List<ChargeReportOutput> charges) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo",               LOGO);
        parameters.put("SUBREPORT_TITLE",    REPORT_TITLE);
        parameters.put("SUBREPORT_DETAIL",   REPORT_DETAIL);
        parameters.put("SUBREPORT_INVOICES", REPORT_INVOICES);

        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(charges);
            var jasperPrint = JasperFillManager.fillReport(REPORT_MAIN, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar PDF de cobrança", e);
        }
    }

    private static BufferedImage loadLogo() {
        try {
            return ImageIO.read(Objects.requireNonNull(
                ChargeReportService.class.getResourceAsStream("/images/acal.jpg")));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }
}

