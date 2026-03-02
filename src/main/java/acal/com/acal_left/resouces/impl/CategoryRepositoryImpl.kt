package acal.com.acal_left.resouces.impl

import acal.com.acal_left.core.model.CategoryDomain
import acal.com.acal_left.core.repository.CategoryRepository
import acal.com.acal_left.resouces.repository.CategoryJpaRepository
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryImpl(
    private val categoryJpaRepository: CategoryJpaRepository
) : CategoryRepository {

    override fun findAllByOrderByNameAsc(): List<CategoryDomain?> {
        return categoryJpaRepository.findAllByOrderByNameAsc() as List<CategoryDomain?>
    }
}


