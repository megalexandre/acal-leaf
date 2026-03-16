package acal.com.acal_left.core.usecase.invoice;

import acal.com.acal_left.core.model.Hydrometer;
import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceGenerateFilter;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.repository.InvoiceRepository;
import lombok.val;
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
            .filter(it -> it.getCategory() != null && it.getCategory().getIsHydrometer())
            .toList();

        if (!hydrometerInvoices.isEmpty()) {
            val lastPeriodFilter = InvoiceQuery.builder()
                    .period(filter.getPeriod().minusMonths(1))
                    .build();

            var previous = invoiceRepository.listInvoices(lastPeriodFilter);

            hydrometerInvoices.forEach(invoice -> {
                if (invoice.getHydrometer() == null) {
                    invoice.setHydrometer(Hydrometer.builder().build());
                };

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
