package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.InvoiceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Integer> {

    @Modifying
    @Transactional
    @Query("""
            UPDATE InvoiceEntity i
            SET i.paidAt = :paidAt,
                i.paidByPix = :paidByPix,
                i.paidWithAlternativeBill = :paidWithAlternativeBill
            WHERE i.id = :id
        """
    )
    void updatePayedAt(
       @Param("id") Integer id,
       @Param("paidAt") LocalDateTime paidAt,
       @Param("paidByPix") Boolean paidByPix,
       @Param("paidWithAlternativeBill") Boolean paidWithAlternativeBill
    );

    @Query("""
            SELECT COUNT(i) FROM InvoiceEntity i
            JOIN i.personAddress pa
            JOIN pa.person p
            JOIN pa.address a
            JOIN pa.category c
            LEFT JOIN i.hydrometer h
            WHERE (:id IS NULL OR i.id = :id)
                AND (:categoryId IS NULL OR c.id = :categoryId)
                AND (:period IS NULL OR i.period = :period)
                AND (:dueDate IS NULL OR i.dueDate = :dueDate)
                AND (:addressId IS NULL OR a.id = :addressId)
                AND (:personId IS NULL OR p.id = :personId)
                AND (:periodStart is NULL AND :periodEnd IS NULL OR i.paidAt BETWEEN :periodStart AND :periodEnd)
                AND (:paid IS NULL
                    OR (:paid = true AND i.paidAt IS NOT NULL)
                    OR (:paid = false AND i.paidAt IS NULL)
                )
                AND (:paidByPix IS NULL OR i.paidByPix = :paidByPix)
            """)
    long countInvoices(
        @Param("id") Integer id,
        @Param("period") LocalDate period,
        @Param("dueDate") LocalDateTime dueDate,
        @Param("categoryId") Integer categoryId,
        @Param("addressId") Integer addressId,
        @Param("personId") Integer personId,
        @Param("periodStart") LocalDateTime periodStart,
        @Param("periodEnd") LocalDateTime periodEnd,
        @Param("paidByPix") Boolean paidByPix,
        @Param("paid") Boolean paid
    );

    @Query("""
            SELECT i FROM InvoiceEntity i
            JOIN FETCH i.personAddress pa
            JOIN FETCH pa.person p
            JOIN FETCH pa.address a
            JOIN FETCH pa.category c
            LEFT JOIN FETCH i.hydrometer h
            WHERE (:id IS NULL OR i.id = :id)
                AND (:categoryId IS NULL OR c.id = :categoryId)
                AND (:period IS NULL OR i.period = :period)
                AND (:dueDate IS NULL OR i.dueDate = :dueDate)
                AND (:addressId IS NULL OR a.id = :addressId)
                AND (:personId IS NULL OR p.id = :personId)
                AND (:periodStart is NULL AND :periodEnd IS NULL OR i.paidAt BETWEEN :periodStart AND :periodEnd)
                AND (:paid IS NULL
                    OR (:paid = true AND i.paidAt IS NOT NULL)
                    OR (:paid = false AND i.paidAt IS NULL)
                )
                AND (:paidByPix IS NULL OR i.paidByPix = :paidByPix)
            ORDER BY i.period DESC, a.type ASC, a.name ASC
            """)
    List<InvoiceEntity> findInvoicesWithPagination(
        @Param("id") Integer id,
        @Param("period") LocalDate period,
        @Param("dueDate") LocalDateTime dueDate,
        @Param("categoryId") Integer categoryId,
        @Param("addressId") Integer addressId,
        @Param("personId") Integer personId,
        @Param("periodStart") LocalDateTime periodStart,
        @Param("periodEnd") LocalDateTime periodEnd,
        @Param("paid") Boolean paid,
        @Param("paidByPix") Boolean paidByPix,
        Pageable pageable
    );

}
