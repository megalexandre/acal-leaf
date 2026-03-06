package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkJpaRepository extends JpaRepository<LinkEntity, Integer> {

    @Query("""
        SELECT DISTINCT l FROM LinkEntity l
            JOIN FETCH l.address a
            JOIN FETCH l.category c
            JOIN FETCH l.person p
        WHERE
            (:inactive IS NULL OR l.inactive = :inactive)
        """
    )
    List<LinkEntity> findAllWithEagerLoading(
        @Param("inactive") Boolean inactive
    );
}
