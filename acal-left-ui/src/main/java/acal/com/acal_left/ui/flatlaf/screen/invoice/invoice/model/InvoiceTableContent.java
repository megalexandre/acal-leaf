package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Invoice;
import lombok.Data;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Data
public class InvoiceTableContent {
    private static final DateTimeFormatter PERIOD_FORMATTER = ofPattern("MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = ofPattern("dd/MM/yyyy");

    private final Integer id;
    private final String number;
    private final String partner;
    private final String period;
    private final String address;
    private final String paid;
    private final String dueDate;
    private final Boolean isOverDue;

    private final Invoice item;

    public InvoiceTableContent(Invoice item) {
        this.id = item.getId();
        this.number = item.getId().toString();
        this.partner = item.getPerson().getName();
        this.address = item.getAddress().getFullAddress();
        this.dueDate = item.getDueDate().format(DATE_TIME_FORMATTER);
        this.period = item.getPeriod() == null ? "" : item.getPeriod().format(PERIOD_FORMATTER);
        this.paid = item.isPaid() ? "Sim" : "Não";
        this.isOverDue = item.isOverDue();
        this.item = item;
    }

}