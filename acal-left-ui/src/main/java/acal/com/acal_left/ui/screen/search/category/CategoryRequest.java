package acal.com.acal_left.ui.screen.search.category;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.shared.model.MemberGroup;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategoryRequest {
    private Integer id;
    private String name;
    private BigDecimal amountWater;
    private BigDecimal amountPartner;
    private MemberGroup memberGroup;

    public Category toCategory(){
        return Category.builder()
                .id(id)
                .name(name)
                .amountWater(amountWater)
                .amountPartner(amountPartner)
                .memberGroup(memberGroup)
                .build();
    }
}
