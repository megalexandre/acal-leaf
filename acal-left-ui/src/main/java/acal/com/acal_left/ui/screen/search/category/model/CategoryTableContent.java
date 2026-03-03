package acal.com.acal_left.ui.screen.search.category.model;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Data;

@Data
public class CategoryTableContent {
    private final Integer id;
    private final String name;
    private final String amountWater;
    private final String amountPartner;
    private final String total;
    private final String memberGroup;
    private final Category categoryEntityEntity;

    public CategoryTableContent(Category categoryEntity) {
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
        this.amountWater = BigDecimalUtil.toBRL(categoryEntity.getAmountWater());
        this.amountPartner = BigDecimalUtil.toBRL(categoryEntity.getAmountPartner());
        this.total = BigDecimalUtil.toBRL(categoryEntity.getAmountWater().add(categoryEntity.getAmountPartner()));
        this.memberGroup = categoryEntity.getMemberGroup().getDescription();
        this.categoryEntityEntity = categoryEntity;
    }

}