package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCreateUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoiceCreateUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> execute(List<Invoice> invoices) {
        return invoiceRepository.save(invoices);
    }

}

