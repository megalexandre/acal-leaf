package acal.com.acal_left.ui.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Loads a JasperReport preferring the pre-compiled .jasper file from the
 * classpath (generated at build time). Falls back to compiling the .jrxml
 * at runtime when the .jasper is not found (e.g. running from source in the IDE).
 */
public class ReportLoader {

    /**
     * @param jrxmlPath classpath path to the .jrxml, e.g. "/reports/charge.jrxml"
     * @return compiled JasperReport, cached by the caller
     */
    public static JasperReport load(String jrxmlPath) {
        String jasperPath = toJasperPath(jrxmlPath);

        // 1) Try pre-compiled .jasper
        try (InputStream jasperStream = ReportLoader.class.getResourceAsStream(jasperPath)) {
            if (jasperStream != null) {
                return (JasperReport) JRLoader.loadObject(jasperStream);
            }
        } catch (JRException | IOException e) {
            // fall through to compile
        }

        // 2) Fallback: compile from .jrxml at runtime
        try (InputStream jrxmlStream = ReportLoader.class.getResourceAsStream(jrxmlPath)) {
            if (jrxmlStream == null) {
                throw new RuntimeException("Report template not found: " + jrxmlPath);
            }
            return JasperCompileManager.compileReport(jrxmlStream);
        } catch (JRException | IOException e) {
            throw new RuntimeException("Failed to compile report: " + jrxmlPath, e);
        }
    }

    private static String toJasperPath(String jrxmlPath) {
        return jrxmlPath.replaceAll("\\.jrxml$", ".jasper");
    }
}

