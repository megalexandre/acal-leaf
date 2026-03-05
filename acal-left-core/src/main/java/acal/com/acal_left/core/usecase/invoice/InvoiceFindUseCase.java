package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class InvoiceFindUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoiceFindUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Page<Invoice> execute(InvoiceQuery invoiceQuery) {
        return invoiceRepository.findInvoices(invoiceQuery);
    }

}

