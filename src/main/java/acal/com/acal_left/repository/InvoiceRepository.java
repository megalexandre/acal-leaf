package acal.com.acal_left.repository;

import acal.com.acal_left.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(
        "SELECT i FROM Invoice i WHERE " +
        "(:id IS NULL OR i.id = :id) "
    )
    List<Invoice> findInvoices(
            @Param("id") Integer id
    );
}
