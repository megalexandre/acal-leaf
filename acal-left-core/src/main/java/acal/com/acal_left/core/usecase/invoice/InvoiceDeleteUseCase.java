package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDeleteUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoiceDeleteUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void execute(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

}

