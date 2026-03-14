package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.ChargeReportOutput;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChargeReportService {

    public byte[] createReport(List<ChargeReportOutput> charges) {

        String templatePath = "/reports/charge.jrxml";
        InputStream templateStream = ChargeReportService.class.getResourceAsStream(templatePath);
        if (templateStream == null) {
            throw new RuntimeException("Template não encontrado: " + templatePath);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo", getLogo());

        try {
            compile("/reports/charge_title.jrxml",    parameters, "SUBREPORT_TITLE");
            compile("/reports/charge_detail.jrxml",   parameters, "SUBREPORT_DETAIL");
            compile("/reports/charge_invoices.jrxml", parameters, "SUBREPORT_INVOICES");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao compilar subreports de cobrança", e);
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(charges);
            var jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar PDF de cobrança", e);
        }
    }

    private static void compile(String path, Map<String, Object> params, String key) throws JRException {
        InputStream stream = ChargeReportService.class.getResourceAsStream(path);
        if (stream != null) {
            JasperReport report = JasperCompileManager.compileReport(stream);
            params.put(key, report);
        }
    }

    private BufferedImage getLogo() {
        try {
            return ImageIO.read(Objects.requireNonNull(ChargeReportService.class.getResourceAsStream("/images/acal.jpg")));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }
}
