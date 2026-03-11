package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicePayUseCase {

    private final InvoiceRepository invoiceRepository;

    public InvoicePayUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice execute(Invoice invoice) {
        return invoiceRepository.pay(invoice);
    }

}

