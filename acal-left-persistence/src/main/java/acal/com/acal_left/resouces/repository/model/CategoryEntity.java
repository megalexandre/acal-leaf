package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.shared.model.MemberGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount_water", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountWater;

    @Column(name = "amount_partner", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountPartner;

    @Column(name = "group_id", nullable = false)
    private Integer memberGroup;

    @Column(name = "has_hydrometer", nullable = false)
    private boolean isHydrometer = false;

    public static Category toEntity(CategoryEntity entity) {
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .amountWater(entity.getAmountWater())
                .amountPartner(entity.getAmountPartner())
                .memberGroup(MemberGroup.from(entity.getMemberGroup()))
                .isHydrometer(entity.isHydrometer())
                .build();
    }

    public static CategoryEntity fromEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .amountWater(category.getAmountWater())
                .amountPartner(category.getAmountPartner())
                .memberGroup(category.getMemberGroup().getValue())
                .isHydrometer(category.getIsHydrometer())
                .build();
    }
    @Override
    public String toString() {
        return "id:" + id;
    }
}

