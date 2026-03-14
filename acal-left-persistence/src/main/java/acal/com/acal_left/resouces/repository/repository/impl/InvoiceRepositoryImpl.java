package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.core.model.WaterAnalysisItem;
import acal.com.acal_left.core.model.filter.InvoiceGenerateFilter;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.repository.InvoiceRepository;
import acal.com.acal_left.resouces.repository.model.CategoryEntity;
import acal.com.acal_left.resouces.repository.model.InvoiceEntity;
import acal.com.acal_left.resouces.repository.model.PersonEntity;
import acal.com.acal_left.resouces.repository.model.WaterAnalysisItemEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.InvoiceJpaRepository;
import acal.com.acal_left.resouces.repository.repository.jpa.LinkJpaRepository;
import acal.com.acal_left.resouces.repository.repository.jpa.WaterAnalysisJpaRepository;
import acal.com.acal_left.shared.model.WaterParameterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final InvoiceJpaRepository invoiceJpaRepository;
    private final WaterAnalysisJpaRepository waterAnalysisJpaRepository;
    private final LinkJpaRepository linkJpaRepository;

    public InvoiceRepositoryImpl(
            InvoiceJpaRepository invoiceJpaRepository,
            WaterAnalysisJpaRepository waterAnalysisJpaRepository,
            LinkJpaRepository linkJpaRepository
        ) {
        this.invoiceJpaRepository = invoiceJpaRepository;
        this.waterAnalysisJpaRepository = waterAnalysisJpaRepository;
        this.linkJpaRepository = linkJpaRepository;
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceJpaRepository.deleteById(invoice.getId());
    }

    @Override
    public List<Invoice> listInvoices(InvoiceQuery invoiceQuery) {
        var invoices = invoiceJpaRepository.findInvoicesWithPagination(
                        invoiceQuery.getId(),
                        invoiceQuery.getPeriod(),
                        invoiceQuery.getDueDate(),
                        invoiceQuery.getCategoryId(),
                        invoiceQuery.getAddressId(),
                        invoiceQuery.getPersonId(),
                        invoiceQuery.getPeriodStart(),
                        invoiceQuery.getPeriodEnd(),
                        invoiceQuery.getPaid(),
                        Pageable.unpaged()
                ).stream()
                .map(InvoiceEntity::toDomain)
                .toList();

        var periods = invoices.stream().map(Invoice::getPeriod).distinct().toList();
        var items = waterAnalysisJpaRepository.findByPeriodIn(periods);

        var waterAnalysisByPeriod = items.stream()
                .collect(Collectors.groupingBy(
                        WaterAnalysisItemEntity::getPeriod,
                        Collectors.mapping(this::toWaterAnalysisItem, Collectors.toList())
                ));

        invoices.forEach(invoice -> {
            var analysisItems = waterAnalysisByPeriod.getOrDefault(invoice.getPeriod(), List.of());
            invoice.setWaterAnalysis(
                    WaterAnalysis.builder()
                            .analysis(analysisItems)
                            .build()
            );
        });

        return invoices;
    }

    @Override
    public List<Invoice> listInvoicesToGenerate(InvoiceGenerateFilter filter) {
        var period = filter.getReference().atDay(1);

        Boolean hasHydrometer = switch (filter.getType()) {
            case WITH_HYDROMETER    -> true;
            case WITHOUT_HYDROMETER -> false;
            case ALL                -> null;
        };

        return linkJpaRepository.findLinksWithoutInvoiceForPeriod(period, hasHydrometer)
                .stream()
                .map(link -> Invoice.builder()
                        .number(link.getNumber())
                        .person(PersonEntity.toEntity(link.getPerson()))
                        .address(AddressRepositoryImpl.toEntity(link.getAddress()))
                        .category(CategoryEntity.toEntity(link.getCategory()))
                        .period(period)
                        .amountPartner(link.getCategory().getAmountPartner())
                        .amountWater(link.getCategory().getAmountWater())
                        .build()
                )
                .toList();
    }

    @Override
    public Invoice pay(Invoice invoice) {
        invoiceJpaRepository.updatePayedAt(invoice.getId(), invoice.getPaidAt());
        return invoice;
    }

    @Override
    public Page<Invoice> paginateInvoices(InvoiceQuery invoiceQuery) {
        long total = invoiceJpaRepository.countInvoices(
                invoiceQuery.getId(),
                invoiceQuery.getPeriod(),
                invoiceQuery.getDueDate(),
                invoiceQuery.getCategoryId(),
                invoiceQuery.getAddressId(),
                invoiceQuery.getPersonId(),
                invoiceQuery.getPeriodStart(),
                invoiceQuery.getPeriodEnd(),
                invoiceQuery.getPaid()
        );

        var invoices = invoiceJpaRepository.findInvoicesWithPagination(
                invoiceQuery.getId(),
                invoiceQuery.getPeriod(),
                invoiceQuery.getDueDate(),
                invoiceQuery.getCategoryId(),
                invoiceQuery.getAddressId(),
                invoiceQuery.getPersonId(),
                invoiceQuery.getPeriodStart(),
                invoiceQuery.getPeriodEnd(),
                invoiceQuery.getPaid(),
                invoiceQuery.getPageable()
        ).stream()
         .map(InvoiceEntity::toDomain)
         .toList();

        var periods = invoices.stream().map(Invoice::getPeriod).distinct().toList();
        var items = waterAnalysisJpaRepository.findByPeriodIn(periods);

        var waterAnalysisByPeriod = items.stream()
                .collect(Collectors.groupingBy(
                        WaterAnalysisItemEntity::getPeriod,
                        Collectors.mapping(this::toWaterAnalysisItem, Collectors.toList())
                ));

        invoices.forEach(invoice -> {
            var analysisItems = waterAnalysisByPeriod.getOrDefault(invoice.getPeriod(), List.of());
            invoice.setWaterAnalysis(
                WaterAnalysis.builder()
                    .analysis(analysisItems)
                    .build()
            );
        });

        return new PageImpl<>(
            invoices,
            invoiceQuery.getPageable(),
            total
        );
    }

    private WaterAnalysisItem toWaterAnalysisItem(WaterAnalysisItemEntity entity) {
        return WaterAnalysisItem.builder()
                .id(entity.getId())
                .type(WaterParameterType.fromId(entity.getParameterTypeId()))
                .required(entity.getRequired())
                .analyzed(entity.getAnalyzed())
                .conformity(entity.getConformity())
                .createTime(entity.getPeriod())
                .build();
    }
}
