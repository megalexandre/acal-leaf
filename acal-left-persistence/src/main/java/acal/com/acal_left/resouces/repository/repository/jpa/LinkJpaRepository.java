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
        SELECT l FROM LinkEntity l
            JOIN FETCH l.address a
            JOIN FETCH l.category c
            JOIN FETCH l.person p
        WHERE
           (:inactive IS NULL OR l.inactive = :inactive) AND
           (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT(:name, '%'))) AND
           (:address IS NULL OR\s
               LOWER(CONCAT(COALESCE(a.type, ''), ' ', COALESCE(a.name, '')))\s
               LIKE LOWER(CONCAT('%', :address))
           )
        """
    )
    List<LinkEntity> findAllWithEagerLoading(
        @Param("inactive") Boolean inactive,
        @Param("name") String name,
        @Param("address") String address
    );

    @Query("""
        SELECT l FROM LinkEntity l
            JOIN FETCH l.address a
            JOIN FETCH l.category c
            JOIN FETCH l.person p
        WHERE l.inactive = false
            AND l.id NOT IN (
                SELECT i.personAddress.id FROM InvoiceEntity i
                WHERE i.period = :period
            )
            AND (:hasHydrometer IS NULL OR c.isHydrometer = :hasHydrometer)
        """)
    List<LinkEntity> findLinksWithoutInvoiceForPeriod(
            @Param("period") java.time.LocalDate period,
            @Param("hasHydrometer") Boolean hasHydrometer
    );
}
