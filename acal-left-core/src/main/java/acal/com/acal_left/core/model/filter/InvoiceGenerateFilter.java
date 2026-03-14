package acal.com.acal_left.core.model.filter;

import acal.com.acal_left.shared.model.GenerateInvoiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceGenerateFilter {
    private YearMonth reference;
    private GenerateInvoiceType type;
}
