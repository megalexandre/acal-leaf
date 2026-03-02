package acal.com.acal_left.ui.screen.search.category.model;

import acal.com.acal_left.resouces.model.CategoryModel;
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
    private final CategoryModel categoryModelEntity;

    public CategoryRecord(CategoryModel categoryModel) {
        this.id = categoryModel.getId();
        this.name = categoryModel.getName();
        this.amountWater = BigDecimalUtil.toBRL(categoryModel.getAmountWater());
        this.amountPartner = BigDecimalUtil.toBRL(categoryModel.getAmountPartner());
        this.total = BigDecimalUtil.toBRL(categoryModel.getAmountWater().add(categoryModel.getAmountPartner()));
        this.category = categoryModel.getGroup().getDescription();
        this.categoryModelEntity = categoryModel;
    }

}