package acal.com.acal_left.ui.flatlaf.screen.invoice.create.model;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Data;

@Data
public class InvoiceGenerateTableContent {

    private final String partner;
    private final String address;
    private final String category;
    private final String total;
    private final boolean hasHydrometer;
    private Long hydrometerStart;
    private Long hydrometerEnd;
    private boolean generate;
    private final Invoice item;

    public InvoiceGenerateTableContent(Invoice item) {
        this.partner = item.getPerson().getName();
        this.address = item.getAddress().getFullAddress() + " " + item.getNumber();
        this.category = item.getCategory().getFullName();
        this.total = BigDecimalUtil.toBRL(item.totalAmount());
        this.hasHydrometer = item.getCategory().getIsHydrometer();
        this.hydrometerStart = !item.getCategory().getIsHydrometer() ? 0L : null;
        this.hydrometerEnd =  !item.getCategory().getIsHydrometer() ? 0L : null;
        this.generate = true;
        this.item = item;
    }

}