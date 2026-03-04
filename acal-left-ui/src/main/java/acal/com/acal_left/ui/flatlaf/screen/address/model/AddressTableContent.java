package acal.com.acal_left.ui.flatlaf.screen.address.model;

import acal.com.acal_left.core.model.Address;
import lombok.Data;

@Data
public class AddressTableContent {

    private final Integer id;
    private final String label;
    private final Address item;

    public AddressTableContent(Address item) {
        this.id = item.getId();
        this.label = item.getType().trim() + " " + item.getName().trim();
        this.item = item;
    }

}