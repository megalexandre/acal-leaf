package acal.com.acal_left.ui.flatlaf.screen.person.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonViewModel {

    private Integer id;
    private String name;
    private String document;

}
