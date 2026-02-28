package acal.com.acal_left.core.usecase;

import acal.com.acal_left.model.Category;
import acal.com.acal_left.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public FindAllCategoryUseCase( CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> execute() {
        return categoryRepository.findAllByOrderByNameAsc();
    }
}
