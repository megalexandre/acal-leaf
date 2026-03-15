package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.Hydrometer;
import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.resouces.repository.repository.impl.AddressRepositoryImpl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "conta")
@Data
@NamedEntityGraph(
    name = "InvoiceEntity.withAllRelationships",
    attributeNodes = {
        @NamedAttributeNode("personAddress"),
        @NamedAttributeNode("hydrometer")
    }
)
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "id_endereco_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LinkEntity personAddress;

    @Column(name = "data_referente")
    private LocalDate period;

    @Column(name = "data_pagamento")
    private LocalDateTime paidAt;

    @Column(name = "data_vencimento")
    private LocalDateTime dueDate;

    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL)
    private HydrometerEntity hydrometer;

    @Column(name = "amount_partner", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountPartner;

    @Column(name = "amount_water", nullable = false)
    private BigDecimal amountWater;

    public static Invoice toDomain(InvoiceEntity entity) {
        return Invoice.builder()
                .number(entity.getPersonAddress().getNumber())
                .person(PersonEntity.toEntity(entity.getPersonAddress().getPerson()))
                .address(AddressRepositoryImpl.toEntity(entity.getPersonAddress().getAddress()))
                .dueDate(entity.getDueDate())
                .paidAt(entity.getPaidAt())
                .period(entity.getPeriod())
                .category(CategoryEntity.toEntity(entity.getPersonAddress().getCategory()))
                .amountPartner(getBigDecimalValue(entity.getAmountPartner()))
                .amountWater(getBigDecimalValue(entity.getAmountWater()))
                .id(entity.getId())
                .linkId(entity.getPersonAddress().getId())
                .hydrometer(
                    Hydrometer.builder()
                        .consumptionStart(entity.getHydrometer() != null ? entity.getHydrometer().getConsumptionStart() : 0D)
                        .consumptionEnd(entity.getHydrometer() != null ? entity.getHydrometer().getConsumptionEnd() : 0D)
                    .build()
                )
                .build();
    }

    public static InvoiceEntity toEntity(Invoice invoice) {
        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(invoice.getId());
        entity.setPeriod(invoice.getPeriod());
        entity.setPaidAt(invoice.getPaidAt());
        entity.setDueDate(invoice.getDueDate());
        entity.setAmountPartner(invoice.getAmountPartner());
        entity.setAmountWater(invoice.getAmountWater());

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setId(invoice.getLinkId());
        entity.setPersonAddress(linkEntity);

        if (invoice.getHydrometer() != null) {
            HydrometerEntity hydrometerEntity = new HydrometerEntity();
            hydrometerEntity.setConsumptionStart(invoice.getHydrometer().getConsumptionStart());
            hydrometerEntity.setConsumptionEnd(invoice.getHydrometer().getConsumptionEnd());
            hydrometerEntity.setEntity(entity);
            entity.setHydrometer(hydrometerEntity);
        }

        return entity;
    }



    private static BigDecimal getBigDecimalValue(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "id:" + id;
    }

}
