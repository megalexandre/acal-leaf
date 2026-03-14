package acal.com.acal_left.ui.report;

import acal.com.acal_left.ui.report.out.ChargeReportOutput;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChargeReportManager {

    public byte[] generatePdfReport(InputStream reportTemplate,
                                    List<ChargeReportOutput> dataset,
                                    Map<String, Object> parameters) {
        try {
            var jasperReport = JasperCompileManager.compileReport(reportTemplate);

            Collection<Map<String, ?>> rows = new ArrayList<>();
            for (ChargeReportOutput row : dataset) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("self", row);
                rows.add(entry);
            }

            var dataSource = new JRMapCollectionDataSource(rows);
            var jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(parameters), dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de cobrança", e);
        }
    }
}
