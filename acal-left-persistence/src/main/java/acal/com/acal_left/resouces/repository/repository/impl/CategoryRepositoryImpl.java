package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.repository.CategoryRepository;
import acal.com.acal_left.resouces.repository.model.CategoryEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.CategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static acal.com.acal_left.resouces.repository.model.CategoryEntity.fromEntity;
import static acal.com.acal_left.resouces.repository.model.CategoryEntity.toEntity;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryRepositoryImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public List<Category> findAllByOrderByNameAsc() {
        return categoryJpaRepository.findAllByOrderByNameAsc()
                .stream().map(CategoryEntity::toEntity).toList();
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = categoryJpaRepository.save(fromEntity(category));
        return toEntity(categoryEntity);
    }

}

