package acal.com.acal_left.core.model;

import acal.com.acal_left.shared.model.ChargeLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Charge {

    private static final int DEACTIVATION_DAYS_THRESHOLD = 90;

    private Address address;
    private Person person;
    private Category category;
    private String number;

    @Getter
    @Builder.Default
    private List<Invoice> invoices = new ArrayList<>();

    public long numberOfInvoices(){
        return invoices.size();
    };

    public ChargeLevel level() {
        boolean hasDeactivationOverdue = invoices.stream()
                .filter(Objects::nonNull)
                .anyMatch(inv -> inv.getDueDate() != null &&
                        inv.getDueDate().isBefore(java.time.LocalDateTime.now().minusDays(DEACTIVATION_DAYS_THRESHOLD)));

        return hasDeactivationOverdue
                ? ChargeLevel.DEACTIVATION_NOTICE
                : ChargeLevel.OVERDUE_NOTICE;
    }

    public BigDecimal getTotal() {

        return invoices.stream()
                .filter(Objects::nonNull)
                .map(Invoice::totalAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}