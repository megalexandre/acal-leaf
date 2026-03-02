package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Integer> {

    @Query("SELECT DISTINCT i FROM Invoice i " +
            "JOIN FETCH i.personAddressEntity pa " +
            "JOIN FETCH pa.personEntity p " +
            "LEFT JOIN FETCH p.partnerEntity " +
            "JOIN FETCH pa.addressEntity a " +
            "JOIN FETCH pa.categoryEntity c " +
            "WHERE (:id IS NULL OR i.id = :id)" +

            "AND (:categoryId IS NULL OR c.id = :categoryId)" +
            "AND (:addressId IS NULL OR a.id = :addressId)" +
            "AND (:partnerId IS NULL OR p.partner.id = :partnerId)"
    )

    List<InvoiceEntity> findInvoices(
        @Param("id") Integer id,
        @Param("categoryId") Integer categoryId,
        @Param("addressId") Integer addressId,
        @Param("partnerId") Integer partnerId
    );

}
