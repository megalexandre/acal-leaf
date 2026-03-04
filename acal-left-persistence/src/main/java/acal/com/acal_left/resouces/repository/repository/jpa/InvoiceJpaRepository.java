package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.InvoiceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Integer> {

    @Query("""
            SELECT DISTINCT i FROM InvoiceEntity i
            JOIN FETCH i.personAddress pa
            JOIN FETCH pa.person p
            JOIN FETCH p.partner
            JOIN FETCH pa.address a
            JOIN FETCH pa.category c
            WHERE (:id IS NULL OR i.id = :id)
                AND (:categoryId IS NULL OR c.id = :categoryId)
                AND (:addressId IS NULL OR a.id = :addressId)
                AND (:partnerId IS NULL OR p.partner.id = :partnerId)
            ORDER BY i.period, a.type, a.name DESC
            """)

    List<InvoiceEntity> findInvoices(
        @Param("id") Integer id,
        @Param("categoryId") Integer categoryId,
        @Param("addressId") Integer addressId,
        @Param("partnerId") Integer partnerId,
        Pageable pageable
    );

}
