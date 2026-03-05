package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerJpaRepository extends JpaRepository<PartnerEntity, Integer> {

    @Query(
        """
        SELECT p FROM PartnerEntity p
            JOIN FETCH p.person per 
        WHERE (:name IS NULL OR LOWER(per.name) LIKE LOWER(CONCAT(:name, '%'))) 
            ORDER BY per.name ASC
        """
    )
    List<PartnerEntity> findByFilter(@Param("name") String name);
}
