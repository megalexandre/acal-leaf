package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceFilter;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class InvoicePaginateUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoicePaginateUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Page<Invoice> execute(InvoiceFilter invoiceFilter) {
        return invoiceRepository.paginateInvoices(invoiceFilter);
    }

}

