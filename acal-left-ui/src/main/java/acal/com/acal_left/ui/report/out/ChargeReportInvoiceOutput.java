package acal.com.acal_left.ui.report.out;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import acal.com.acal_left.shared.LocalDateUtil;
import lombok.Getter;

@Getter
public class ChargeReportInvoiceOutput {

    private final String number;
    private final String reference;
    private final String dueDate;
    private final String amount;

    public ChargeReportInvoiceOutput(Invoice invoice) {
        this.number = invoice.getId().toString();
        this.reference = LocalDateUtil.formatPeriod(invoice.getPeriod());
        this.dueDate = LocalDateTimeUtil.formatDateTime(invoice.getDueDate());
        this.amount = BigDecimalUtil.toBRL(invoice.totalAmount());
    }
}

