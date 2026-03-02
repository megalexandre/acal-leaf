package acal.com.acal_left.core.usecase;

import acal.com.acal_left.resouces.model.CategoryModel;
import acal.com.acal_left.resouces.repository.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryFindAllUseCase {

    private final CategoryJpaRepository categoryRepository;

    public CategoryFindAllUseCase(CategoryJpaRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryModel> execute() {
        return categoryRepository.findAllByOrderByNameAsc();
    }
}
