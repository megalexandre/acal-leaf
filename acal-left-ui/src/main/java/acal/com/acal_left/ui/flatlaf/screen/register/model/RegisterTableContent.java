package acal.com.acal_left.ui.flatlaf.screen.register.model;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import lombok.Data;

@Data
public class RegisterTableContent {

    private final String number;
    private final String partner;
    private final String paymentDate;
    private final String amount;

    public RegisterTableContent(Invoice invoice) {
        this.number = invoice.getId().toString();
        this.partner = invoice.getPerson().getName();
        this.paymentDate = LocalDateTimeUtil.formatDateTime(invoice.getPaidAt());
        this.amount = BigDecimalUtil.toBRL(invoice.totalAmount());
    }

}