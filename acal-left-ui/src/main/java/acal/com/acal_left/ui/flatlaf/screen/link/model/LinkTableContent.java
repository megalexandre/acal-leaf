package acal.com.acal_left.ui.flatlaf.screen.link.model;

import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Data;

@Data
public class LinkTableContent {

    private final Integer id;
    private final String name;
    private final String address;
    private final String active;
    private final String category;
    private final String amount;
    private final Link item;

    public LinkTableContent(Link item) {
        this.id = item.getId();
        this.name = item.getPerson().getName();
        this.address = item.getNumber() + ", " + item.getAddress().getFullAddress();
        this.active = item.getActive() ? "Sim" : "Não";
        this.category = item.getCategory().getFullName();
        this.amount = BigDecimalUtil.toBRL(item.getCategory().getAmount()) ;
        this.item = item;
    }

}