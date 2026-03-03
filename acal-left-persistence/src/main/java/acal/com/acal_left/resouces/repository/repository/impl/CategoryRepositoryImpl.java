package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.repository.CategoryRepository;
import acal.com.acal_left.resouces.repository.model.CategoryEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.CategoryJpaRepository;
import acal.com.acal_left.shared.model.MemberGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryRepositoryImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public List<Category> findAllByOrderByNameAsc() {
        return categoryJpaRepository.findAllByOrderByNameAsc()
                .stream().map(CategoryRepositoryImpl::toEntity).toList();
    }

    public static Category toEntity(CategoryEntity entity) {
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .amountWater(entity.getAmountWater())
                .amountPartner(entity.getAmountPartner())
                .memberGroup(MemberGroup.from(entity.getMemberGroup()))
                .build();
    }

    public static CategoryEntity fromEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .amountWater(category.getAmountWater())
                .amountPartner(category.getAmountPartner())
                .memberGroup(category.getMemberGroup().getValue())
                .build();
    }

}

