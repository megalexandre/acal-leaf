package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceViewModel {
    private Integer number;
    private String partner;
    private String period;
}
