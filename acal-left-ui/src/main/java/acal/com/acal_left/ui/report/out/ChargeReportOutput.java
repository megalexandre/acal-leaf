package acal.com.acal_left.ui.report.out;

import acal.com.acal_left.core.model.Charge;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Getter;

import java.util.List;

@Getter
public class ChargeReportOutput {

    private final String partnerName;
    private final String address;
    private final String addressNumber;
    private final String category;
    private final String total;
    private final String level;
    private final List<ChargeReportInvoiceOutput> invoices;

    public ChargeReportOutput(Charge charge) {
        this.partnerName = charge.getPerson().getName();
        this.address = charge.getNumber() + ", " + charge.getAddress().getFullAddress();
        this.addressNumber = charge.getNumber();
        this.category = charge.getCategory().getFullName();
        this.total = BigDecimalUtil.toBRL(charge.getTotal());
        this.level = charge.level().description;
        this.invoices = charge.getInvoices().stream()
                .map(ChargeReportInvoiceOutput::new)
                .toList();
    }
}

