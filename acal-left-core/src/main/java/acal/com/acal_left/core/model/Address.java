package acal.com.acal_left.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private Integer id;
    private String name;
    private String type;

    public String getFullAddress() {
        return type + " " + name;
    }

}
