package acal.com.acal_left.ui.report

import acal.com.acal_left.ui.report.out.InvoiceReportOutput
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource
import java.io.InputStream

class ReportManager {

    fun generatePdfReport(
        jasperReport: JasperReport,
        dataset: Collection<InvoiceReportOutput>,
        parameters: Map<String, Any> = emptyMap()
    ): ByteArray {
        val rows = dataset.map { mapOf("self" to it) }
        val dataSource = JRMapCollectionDataSource(rows)
        val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters.toMutableMap(), dataSource)
        return JasperExportManager.exportReportToPdf(jasperPrint)
    }

    // kept for backward compatibility
    fun generatePdfReport(
        reportTemplate: InputStream,
        dataset: Collection<InvoiceReportOutput>,
        parameters: Map<String, Any> = emptyMap()
    ): ByteArray = generatePdfReport(JasperCompileManager.compileReport(reportTemplate), dataset, parameters)
}