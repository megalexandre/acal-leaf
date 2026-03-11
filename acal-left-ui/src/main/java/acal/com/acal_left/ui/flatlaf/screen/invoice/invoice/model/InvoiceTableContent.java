package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import acal.com.acal_left.shared.LocalDateUtil;
import lombok.Data;

@Data
public class InvoiceTableContent {

    private final Integer id;
    private final String number;
    private final String partner;
    private final String period;
    private final String address;
    private final String paid;
    private final String dueDate;
    private final Boolean isOverDue;
    private final Invoice.Status status;
    private final String total;
    private final Invoice item;

    public InvoiceTableContent(Invoice item) {
        this.id = item.getId();
        this.number = item.getId().toString();
        this.partner = item.getPerson().getName();
        this.address = item.getAddress().getFullAddress() + " " + item.getNumber();
        this.dueDate = LocalDateTimeUtil.formatDateTime(item.getDueDate());
        this.period = LocalDateUtil.formatPeriod(item.getPeriod());
        this.status = item.getStatus();
        this.paid = item.isPaid() ? "Sim" : "Não";
        this.isOverDue = item.isOverDue();
        this.total = BigDecimalUtil.toBRL(item.totalAmount());
        this.item = item;
    }

}