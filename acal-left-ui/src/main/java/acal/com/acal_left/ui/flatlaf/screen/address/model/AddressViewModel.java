package acal.com.acal_left.ui.flatlaf.screen.address.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressViewModel {

    private Integer id;
    private String name;
    private String type;

}
