package acal.com.acal_left.ui.flatlaf.screen.category.model;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.shared.model.MemberGroup;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategoryViewModel {

    private Integer id;
    private String name;
    private BigDecimal amountWater;
    private BigDecimal amountPartner;
    private MemberGroup memberGroup;

}
