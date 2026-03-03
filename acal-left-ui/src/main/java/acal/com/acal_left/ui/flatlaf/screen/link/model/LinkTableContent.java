package acal.com.acal_left.ui.flatlaf.screen.link.model;

import acal.com.acal_left.core.model.Link;
import lombok.Data;

@Data
public class LinkTableContent {

    private final Integer id;
    private final String name;
    private final String number;
    private final String document;
    private final Link item;

    public LinkTableContent(Link item) {
        this.id = item.getId();
        this.name = item.getPartner().getName();
        this.number = item.getNumber();
        this.document = item.getPartner().getDocument();
        this.item = item;
    }

}