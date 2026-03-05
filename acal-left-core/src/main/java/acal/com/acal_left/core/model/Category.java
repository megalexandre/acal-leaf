package acal.com.acal_left.core.model;

import acal.com.acal_left.shared.model.MemberGroup;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Category {

    private Integer id;
    private String name;
    private BigDecimal amountWater;
    private BigDecimal amountPartner;
    private MemberGroup memberGroup;
    private Boolean isHydrometer;

}
