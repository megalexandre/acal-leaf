package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.repository.InvoiceRepository;
import acal.com.acal_left.resouces.repository.model.InvoiceEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.InvoiceJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final InvoiceJpaRepository invoiceJpaRepository;

    public InvoiceRepositoryImpl(InvoiceJpaRepository invoiceJpaRepository) {
        this.invoiceJpaRepository = invoiceJpaRepository;
    }

    @Override
    public List<Invoice> findInvoices(InvoiceQuery invoiceQuery) {
        return invoiceJpaRepository.findInvoices(
                invoiceQuery.getId(),
                invoiceQuery.getCategoryId(),
                invoiceQuery.getAddressId(),
                invoiceQuery.getPartnerId(),
                invoiceQuery.getPageable()
        ).stream().map(InvoiceRepositoryImpl::toEntity).toList();
    }

    public static Invoice toEntity(InvoiceEntity entity) {
        return Invoice.builder()
                .person(PersonRepositoryImpl.toEntity(entity.getPersonAddress().getPerson()))
                .address(AddressRepositoryImpl.toEntity(entity.getPersonAddress().getAddress()))
                .paidAt(entity.getPaidAt())
                .period(entity.getPeriod())
                .id(entity.getId())
                .build();
    }

}


