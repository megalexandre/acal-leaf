package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceFindUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoiceFindUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> execute(InvoiceQuery invoiceQuery) {
        return invoiceRepository.findInvoices(invoiceQuery);
    }
}

