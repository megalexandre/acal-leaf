package acal.com.acal_left.core.usecase.category;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryFindAllUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryFindAllUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> execute() {
        return categoryRepository.findAllByOrderByNameAsc();
    }
}
