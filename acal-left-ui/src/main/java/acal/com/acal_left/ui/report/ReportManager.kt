package acal.com.acal_left.ui.report

import acal.com.acal_left.ui.report.out.InvoiceReportOutput
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource
import java.io.InputStream

class ReportManager {

    fun generatePdfReport(
        reportTemplate: InputStream,
        dataset: Collection<InvoiceReportOutput>,
        parameters: Map<String, Any> = emptyMap()
    ): ByteArray {

        val jasperReport = JasperCompileManager.compileReport(reportTemplate)
        val rows = dataset.map { mapOf("self" to it) }
        val dataSource = JRMapCollectionDataSource(rows)
        val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters.toMutableMap(), dataSource)

        return JasperExportManager.exportReportToPdf(jasperPrint)
    }
}