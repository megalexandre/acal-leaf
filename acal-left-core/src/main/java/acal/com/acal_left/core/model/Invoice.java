package acal.com.acal_left.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private Integer id;
    private Person person;
    private Address address;
    private LocalDate period;

}
