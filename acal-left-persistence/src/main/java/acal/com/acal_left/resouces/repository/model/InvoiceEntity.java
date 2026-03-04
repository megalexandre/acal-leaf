package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.resouces.repository.repository.impl.AddressRepositoryImpl;
import acal.com.acal_left.resouces.repository.repository.impl.PersonRepositoryImpl;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "conta")
@Data
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

    public static Invoice toDomain(InvoiceEntity entity) {
        return Invoice.builder()
                .person(PersonRepositoryImpl.toEntity(entity.getPersonAddress().getPerson()))
                .address(AddressRepositoryImpl.toEntity(entity.getPersonAddress().getAddress()))
                .dueDate(entity.getDueDate())
                .paidAt(entity.getPaidAt())
                .period(entity.getPeriod())
                .id(entity.getId())
                .build();
    }

    @Override
    public String toString() {
        return "id:" + id;
    }

}
