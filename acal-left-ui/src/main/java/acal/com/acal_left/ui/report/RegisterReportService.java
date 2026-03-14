package acal.com.acal_left.ui.report;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.ui.report.out.RegisterReportOutput;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegisterReportService {

    private static final JasperReport REPORT = ReportLoader.load("/reports/register.jrxml");
    private static final BufferedImage LOGO   = loadLogo();

    public byte[] createReport(List<Invoice> invoices, String periodStart, String periodEnd) {
        List<RegisterReportOutput> items = invoices.stream()
                .map(RegisterReportOutput::new)
                .toList();

        BigDecimal grandTotal = invoices.stream()
                .map(Invoice::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo",        LOGO);
        parameters.put("periodStart", periodStart);
        parameters.put("periodEnd",   periodEnd);
        parameters.put("totalCount",  String.valueOf(invoices.size()));
        parameters.put("grandTotal",  BigDecimalUtil.toBRL(grandTotal));

        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
            var jasperPrint = JasperFillManager.fillReport(REPORT, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório de caixa", e);
        }
    }

    private static BufferedImage loadLogo() {
        try {
            return ImageIO.read(Objects.requireNonNull(
                    RegisterReportService.class.getResourceAsStream("/images/acal.jpg")));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }
}
