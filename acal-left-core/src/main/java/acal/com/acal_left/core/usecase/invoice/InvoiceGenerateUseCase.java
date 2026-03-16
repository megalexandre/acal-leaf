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
        var actual = invoiceRepository.listInvoicesToGenerate(filter);
        if (actual == null || actual.isEmpty()) {
            return List.of();
        }

        var hydrometerInvoices = actual.stream()
            .filter(it -> it.getCategory() != null && Boolean.TRUE.equals(it.getCategory().getIsHydrometer()))
            .toList();

        if (!hydrometerInvoices.isEmpty()) {
            var previous = invoiceRepository.listInvoicesToGenerate(filter.previousReference());

            hydrometerInvoices.forEach(invoice -> {
                if (invoice.getHydrometer() == null) return;

                previous.stream()
                    .filter(it -> it.getLinkId().equals(invoice.getLinkId()))
                    .findFirst()
                    .ifPresent(i -> {
                        if (i.getHydrometer() != null) {
                            invoice.getHydrometer().setConsumptionStart(i.getHydrometer().getConsumptionEnd());
                        }
                    });
            });
        }

        return actual;
    }

}
