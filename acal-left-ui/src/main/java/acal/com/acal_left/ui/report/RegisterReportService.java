package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.flatlaf.screen.register.model.RegisterReportInfo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterReportService {

    private static final JasperReport REPORT = ReportLoader.load("/reports/register.jrxml");
    private static final BufferedImage LOGO   = loadLogo();

    public byte[] createReport(RegisterReportInfo info) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo",        LOGO);
        parameters.put("periodStart", info.getPeriodStart());
        parameters.put("periodEnd",   info.getPeriodEnd());
        parameters.put("label",       info.getLabel());
        parameters.put("totalCount",  info.count());

        parameters.put("grandTotal",  info.getTotal());
        parameters.put("paymentType", info.getPaymentMethod());

        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(info.asReport());
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
