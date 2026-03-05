package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.repository.InvoiceRepository;
import acal.com.acal_left.resouces.repository.model.InvoiceEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.InvoiceJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final InvoiceJpaRepository invoiceJpaRepository;

    public InvoiceRepositoryImpl(InvoiceJpaRepository invoiceJpaRepository) {
        this.invoiceJpaRepository = invoiceJpaRepository;
    }

    @Override
    public Page<Invoice> findInvoices(InvoiceQuery invoiceQuery) {
        long total = invoiceJpaRepository.countInvoices(
                invoiceQuery.getId(),
                invoiceQuery.getCategoryId(),
                invoiceQuery.getAddressId(),
                invoiceQuery.getPersonId()
        );

        var invoices = invoiceJpaRepository.findInvoicesWithPagination(
                invoiceQuery.getId(),
                invoiceQuery.getCategoryId(),
                invoiceQuery.getAddressId(),
                invoiceQuery.getPersonId(),
                invoiceQuery.getPageable()
        ).stream()
         .map(InvoiceEntity::toDomain)
         .toList();

        return new PageImpl<>(
            invoices,
            invoiceQuery.getPageable(),
            total
        );
    }

}




