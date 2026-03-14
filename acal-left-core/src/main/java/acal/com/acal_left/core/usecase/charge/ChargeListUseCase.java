package acal.com.acal_left.core.usecase.charge;

import acal.com.acal_left.core.model.Charge;
import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.ChargeKey;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.invoice.InvoiceListUseCase;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChargeListUseCase {

    private final InvoiceListUseCase list;

    public ChargeListUseCase(
            InvoiceListUseCase list) {
        this.list = list;
    }

    public List<Charge> execute(InvoiceQuery query ) {
        List<Invoice> invoices = list.execute(query);
        return getChargesFromInvoices(invoices);
    }

    public List<Charge> getChargesFromInvoices(List<Invoice> invoices) {
        if (invoices == null || invoices.isEmpty()) {
            return List.of();
        }

        Map<ChargeKey, List<Invoice>> groupedInvoices = invoices.stream()
                .collect(Collectors.groupingBy(inv -> ChargeKey.builder()
                        .person(inv.getPerson().getId())
                        .address(inv.getAddress().getId())
                        .number(inv.getNumber())
                        .build()
                ));

        return groupedInvoices.values().stream()
                .map(invoiceList -> {

                    invoiceList.sort(Comparator.comparing(Invoice::getDueDate));
                    Invoice reference = invoiceList.getFirst();

                    return Charge.builder()
                            .person(reference.getPerson())
                            .address(reference.getAddress())
                            .category(reference.getCategory())
                            .invoices(invoiceList)
                            .number(invoiceList.getFirst().getNumber())
                            .build();
                })
                .sorted(Comparator
                        .comparing((Charge c) -> c.getAddress() != null ? c.getAddress().getFullAddress() : "", String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Charge::getNumber, Comparator.nullsLast(Comparator.naturalOrder()))
                )
                .collect(Collectors.toList());
    }

}
