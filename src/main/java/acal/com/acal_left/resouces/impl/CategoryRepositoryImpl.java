package acal.com.acal_left.resouces.impl;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.repository.CategoryRepository;
import acal.com.acal_left.resouces.model.CategoryEntity;
import acal.com.acal_left.resouces.repository.CategoryJpaRepository;
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
        return categoryJpaRepository.findAllByOrderByNameAsc().stream().map(CategoryRepositoryImpl::toEntity).toList();
    }

    public static Category toEntity(CategoryEntity entity) {
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .amountWater(entity.getAmountWater())
                .amountPartner(entity.getAmountPartner())
                .group(entity.getGroup())
                .build();
    }

    public static CategoryEntity fromEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .amountWater(category.getAmountWater())
                .amountPartner(category.getAmountPartner())
                .group(category.getGroup())
                .build();
    }

}

