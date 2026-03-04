package acal.com.acal_left.ui.flatlaf.screen.partner.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerViewModel {

    private Integer id;
    private String name;
    private String document;

}
