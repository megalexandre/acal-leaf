package acal.com.acal_left.core.usecase.category;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorySaveUseCase {

    private final CategoryRepository categoryRepository;

    public CategorySaveUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(Category category) {
        return categoryRepository.save(category);
    }
}
