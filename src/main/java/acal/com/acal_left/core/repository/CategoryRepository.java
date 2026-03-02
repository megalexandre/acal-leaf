package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.CategoryDomain;

import java.util.List;

public interface CategoryRepository {

    List<CategoryDomain> findAllByOrderByNameAsc();

}
