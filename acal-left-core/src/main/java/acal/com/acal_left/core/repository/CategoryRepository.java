package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAllByOrderByNameAsc();
    Category save(Category category);

}
