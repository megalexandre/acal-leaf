package acal.com.acal_left.core.usecase;

import acal.com.acal_left.model.Invoice;
import acal.com.acal_left.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllInvoiceUseCase {

    private final InvoiceRepository repository;

    public FindAllInvoiceUseCase(InvoiceRepository repository) {
        this.repository = repository;
    }

    public List<Invoice> execute() {
        return repository.findAll();
    }
}
