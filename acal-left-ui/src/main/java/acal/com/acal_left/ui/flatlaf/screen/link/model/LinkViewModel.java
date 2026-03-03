package acal.com.acal_left.ui.flatlaf.screen.link.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkViewModel {

    private Integer id;
    private String name;
    private String document;

}
