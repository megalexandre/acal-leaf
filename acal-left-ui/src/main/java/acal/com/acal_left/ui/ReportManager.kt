package acal.com.acal_left.ui

import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import java.io.ByteArrayOutputStream
import java.io.InputStream


class ReportManager {

    fun generatePdfReport(
        reportTemplate: InputStream,
        dataset: Collection<Any>,
        parameters: Map<String, Any> = emptyMap()
    ): ByteArray {
        val jasperReport = JasperCompileManager.compileReport(reportTemplate)
        val dataSource = JRBeanCollectionDataSource(dataset)
        val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters.toMutableMap(), dataSource)
        return exportToPdf(jasperPrint)
    }

    fun generatePdfReportFromCompiledReport(
        compiledReport: InputStream,
        dataset: Collection<Any>,
        parameters: Map<String, Any> = emptyMap()
    ): ByteArray {
        val jasperReport = JasperCompileManager.compileReport(compiledReport)
        val dataSource = JRBeanCollectionDataSource(dataset)
        val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters.toMutableMap(), dataSource)
        return exportToPdf(jasperPrint)
    }

    private fun exportToPdf(jasperPrint: JasperPrint): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val exporter = JRPdfExporter()
        exporter.setExporterInput(SimpleExporterInput(jasperPrint))
        exporter.exporterOutput = SimpleOutputStreamExporterOutput(outputStream)
        exporter.exportReport()
        return outputStream.toByteArray()
    }
}