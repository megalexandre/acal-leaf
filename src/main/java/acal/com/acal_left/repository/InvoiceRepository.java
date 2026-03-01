package acal.com.acal_left.repository;

import acal.com.acal_left.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query("SELECT DISTINCT i FROM Invoice i " +
            "JOIN FETCH i.personAddresses pa " +
            "JOIN FETCH pa.person p " +
            "LEFT JOIN FETCH p.partner " +
            "JOIN FETCH pa.address a " +
            "JOIN FETCH pa.category c " +
            "WHERE (:id IS NULL OR i.id = :id)" +
            "AND (:categoryId IS NULL OR c.id = :categoryId)" +
            "AND (:addressId IS NULL OR a.id = :addressId)" +
            "AND (:partnerId IS NULL OR p.partner.id = :partnerId)"
    )

    List<Invoice> findInvoices(
        @Param("id") Integer id,
        @Param("categoryId") Integer categoryId,
        @Param("addressId") Integer addressId,
        @Param("partnerId") Integer partnerId
    );

}
