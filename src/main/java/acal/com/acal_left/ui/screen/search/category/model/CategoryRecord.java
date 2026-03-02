package acal.com.acal_left.ui.screen.search.category.model;

import acal.com.acal_left.resouces.model.Category;
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

    public CategoryRecord(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.amountWater = BigDecimalUtil.toBRL(category.getAmountWater());
        this.amountPartner = BigDecimalUtil.toBRL(category.getAmountPartner());
        this.total = BigDecimalUtil.toBRL(category.getAmountWater().add(category.getAmountPartner()));
        this.category = category.getGroup().getDescription();
    }

}