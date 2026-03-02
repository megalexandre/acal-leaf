package acal.com.acal_left.core.model;

import acal.com.acal_left.resouces.model.Group;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategoryDomain {

    private Integer id;
    private String name;
    private BigDecimal amountWater;
    private BigDecimal amountPartner;
    private Group group;

}
