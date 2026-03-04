package acal.com.acal_left.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private Integer id;
    private Person person;
    private Address address;
    private LocalDate period;
    private LocalDateTime paidAt;
    private LocalDateTime dueDate;

    public boolean isOverDue() {
        return dueDate != null && LocalDateTime.now().isAfter(dueDate) && !isPaid();
    }

    public boolean isPaid(){
        return paidAt != null;
    }

}
