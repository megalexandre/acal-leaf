package acal.com.acal_left.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    private Integer id;
    private String number;
    private Boolean active;
    private Address address;
    private Person person;
    private Partner partner;
    private Category category;
}
