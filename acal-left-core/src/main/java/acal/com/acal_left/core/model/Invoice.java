package acal.com.acal_left.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static acal.com.acal_left.core.model.Invoice.Status.PAID;
import static java.time.Duration.between;
import static java.time.LocalDateTime.now;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private static final int OVERDUE_DAYS_THRESHOLD = 30;
    private static final int AT_RISK_DAYS_THRESHOLD = 60;

    @Getter
    public enum Status {

        OPEN("Aberta"),
        OVERDUE("Atrasada"),
        AT_RISK("Passível de corte"),
        PAID("Paga");

        private final String name;
        Status(String name) { this.name = name; }
    }

    private Integer id;
    private Person person;
    private Address address;
    private String number;
    private LocalDate period;
    private LocalDateTime paidAt;
    private LocalDateTime dueDate;

    public boolean isPaid(){
        return paidAt != null;
    }

    public boolean isOverDue() {
        return !isPaid() && now().isAfter(dueDate);
    }

    private long getDaysOverdue() {
        if (!isOverDue()) {
            return 0;
        }
        return between(dueDate, now()).toDays();
    }

    public Status getStatus() {
        if (isPaid()) {
            return PAID;
        }

        if (!isOverDue()) {
            return Status.OPEN;
        }

        long daysOverdue = getDaysOverdue();

        if (daysOverdue >= AT_RISK_DAYS_THRESHOLD) {
            return Status.AT_RISK;
        }

        if (daysOverdue >= OVERDUE_DAYS_THRESHOLD) {
            return Status.OVERDUE;
        }

        return Status.OPEN;
    }
}
