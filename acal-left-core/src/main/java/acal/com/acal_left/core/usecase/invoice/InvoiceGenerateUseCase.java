package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceGenerateFilter;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceGenerateUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoiceGenerateUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> execute(InvoiceGenerateFilter filter) {
        return invoiceRepository.listInvoicesToGenerate(filter);
    }

}

