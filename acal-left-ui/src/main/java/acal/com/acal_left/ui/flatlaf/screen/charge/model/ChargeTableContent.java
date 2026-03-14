package acal.com.acal_left.ui.flatlaf.screen.charge.model;

import acal.com.acal_left.core.model.Charge;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.model.ChargeLevel;
import lombok.Data;

@Data
public class ChargeTableContent {

    private final String partner;
    private final String address;
    private final String total;
    private final String status;
    private final Long numberOfInvoices;
    private final ChargeLevel level;

    public ChargeTableContent(Charge charge) {
        this.partner = charge.getPerson().getName();
        this.address = charge.getNumber() + ", " + charge.getAddress().getFullAddress();
        this.total = BigDecimalUtil.toBRL(charge.getTotal());
        this.status = charge.level().description;
        this.level = charge.level();
        this.numberOfInvoices = charge.numberOfInvoices();
    }

}