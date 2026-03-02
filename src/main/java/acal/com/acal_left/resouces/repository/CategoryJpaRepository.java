package acal.com.acal_left.resouces.repository;

import acal.com.acal_left.resouces.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findAllByOrderByNameAsc();
}
