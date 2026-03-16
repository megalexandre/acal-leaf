package acal.com.acal_left.core.model.filter;

import acal.com.acal_left.shared.model.GenerateInvoiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceGenerateFilter {
    private YearMonth reference;
    private GenerateInvoiceType type;
    private Integer addressId;

    public InvoiceGenerateFilter previousReference() {
        return this.toBuilder().reference(this.reference.minusMonths(1)).build();
    }

    public LocalDate getPeriod() {
        return this.reference.atDay(1);
    }


}
