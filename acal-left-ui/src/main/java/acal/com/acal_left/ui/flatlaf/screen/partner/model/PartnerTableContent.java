package acal.com.acal_left.ui.flatlaf.screen.partner.model;

import acal.com.acal_left.core.model.Partner;
import lombok.Data;

@Data
public class PartnerTableContent {

    private final Integer id;
    private final String name;
    private final String document;
    private final Partner partner;

    public PartnerTableContent(Partner item) {
        this.id = item.getId();
        this.name = item.getName();
        this.document = item.getDocument();
        this.partner = item;
    }

}