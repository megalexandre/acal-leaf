package acal.com.acal_left.ui.flatlaf.screen.register.model;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.model.PaymentType;
import acal.com.acal_left.ui.report.out.RegisterReportOutput;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Data
@Getter
@Builder
public class RegisterReportInfo {

    private final String periodStart;
    private final String periodEnd;
    private final String label;
    private final List<Invoice> invoices;
    private final PaymentType paymentType;

    public String getPaymentMethod() {
        return Optional.ofNullable(paymentType)
            .map(it -> "Método de pagamento:" + it.getDescription())
            .orElse("Método de pagamento: Todos");
    }

    public String count(){
        return String.valueOf(invoices.size());
    }

    public String getTotal() {
        return BigDecimalUtil.toBRL(invoices.stream()
                .map(Invoice::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public List<RegisterReportOutput> asReport(){
        return invoices.stream()
                .map(RegisterReportOutput::new)
                .toList();
    }

}
