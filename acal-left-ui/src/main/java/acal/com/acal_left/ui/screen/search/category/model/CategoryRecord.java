package acal.com.acal_left.ui.screen.search.category.model;

import acal.com.acal_left.resouces.model.CategoryEntity;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Data;

@Data
public class CategoryRecord {
    private final Integer id;
    private final String name;
    private final String amountWater;
    private final String amountPartner;
    private final String total;
    private final String category;
    private final CategoryEntity categoryEntityEntity;

    public CategoryRecord(CategoryEntity categoryEntity) {
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
        this.amountWater = BigDecimalUtil.toBRL(categoryEntity.getAmountWater());
        this.amountPartner = BigDecimalUtil.toBRL(categoryEntity.getAmountPartner());
        this.total = BigDecimalUtil.toBRL(categoryEntity.getAmountWater().add(categoryEntity.getAmountPartner()));
        this.category = categoryEntity.getGroup().getDescription();
        this.categoryEntityEntity = categoryEntity;
    }

}