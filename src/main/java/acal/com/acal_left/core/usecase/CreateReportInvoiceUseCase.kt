package acal.com.acal_left.core.usecase

import acal.com.acal_left.core.model.InvoiceQuery
import acal.com.acal_left.model.Invoice
import acal.com.acal_left.repository.InvoiceRepository
import org.springframework.stereotype.Component

@Component
class CreateReportInvoiceUseCase(
    private val invoiceRepository: InvoiceRepository
) {

    fun execute(invoiceQuery: InvoiceQuery): List<Invoice> {
        return invoiceRepository.findInvoices(invoiceQuery.id)
    }
}