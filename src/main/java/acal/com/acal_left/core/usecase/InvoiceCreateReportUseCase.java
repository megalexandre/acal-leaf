package acal.com.acal_left.core.usecase;

import acal.com.acal_left.core.model.InvoiceQuery;
import acal.com.acal_left.model.Invoice;
import acal.com.acal_left.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCreateReportUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoiceCreateReportUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> execute(InvoiceQuery invoiceQuery) {
        return invoiceRepository.findInvoices(
            invoiceQuery.getId(),
            invoiceQuery.getCategoryId(),
            invoiceQuery.getAddressId(),
            invoiceQuery.getPartnerId()
        );
    }
}

