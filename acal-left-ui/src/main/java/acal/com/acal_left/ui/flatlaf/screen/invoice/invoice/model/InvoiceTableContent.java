package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Invoice;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class InvoiceTableContent {
    private static final DateTimeFormatter PERIOD_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    private final Integer id;
    private final String number;
    private final String partner;
    private final String period;
    private final String address;
    private final String paid;
    private final Invoice item;

    public InvoiceTableContent(Invoice item) {
        this.id = item.getId();
        this.number = item.getId().toString();
        this.partner = item.getPerson().getName();
        this.address = item.getAddress().getFullAddress();
        this.period = item.getPeriod() == null ? "" : item.getPeriod().format(PERIOD_FORMATTER);
        this.paid = item.isPaid() ? "Sim" : "Não";
        this.item = item;
    }

}