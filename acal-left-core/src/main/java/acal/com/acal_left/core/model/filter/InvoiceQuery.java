package acal.com.acal_left.core.model.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceQuery {
    private Integer id;
    private Boolean paid;
    private Integer categoryId;
    private Integer addressId;
    private Integer personId;
    private LocalDate period;
    private LocalDateTime dueDate;
    private Pageable pageable;

    private LocalDateTime dueDateStart;
    private LocalDateTime dueDateEnd;

    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;

}
