package acal.com.acal_left.ui.report.out;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import acal.com.acal_left.shared.LocalDateUtil;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
public class RegisterReportOutput {

    private final String number;
    private final String partner;
    private final String period;
    private final String paymentDate;
    private final String amountPartner;
    private final String amountWater;
    private final String amountHydrometer;
    private final String total;
    private final String paymentType;

    public RegisterReportOutput(Invoice invoice) {
        this.number = invoice.getId().toString();
        this.partner = invoice.getPerson().getName();
        this.period = LocalDateUtil.formatPeriod(invoice.getPeriod());
        this.paymentDate = LocalDateTimeUtil.formatDateTime(invoice.getPaidAt());
        this.amountPartner = BigDecimalUtil.toBRL(invoice.getAmountPartner());
        this.amountWater = BigDecimalUtil.toBRL(invoice.getAmountWater());
        this.amountHydrometer = getAmountHydrometer(invoice);
        this.total = BigDecimalUtil.toBRL(invoice.totalAmount());
        this.paymentType = invoice.getPaymentType().getDescription();
    }

    private String getAmountHydrometer(Invoice invoice) {
        return Optional.of(invoice.getHydrometer())
                .map(it -> BigDecimalUtil.toBRL(it.price()) )
                .orElse(BigDecimalUtil.toBRL(BigDecimal.ZERO));
    }

}

