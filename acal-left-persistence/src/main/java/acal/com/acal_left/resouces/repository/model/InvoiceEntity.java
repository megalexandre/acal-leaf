package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.*;
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

    @Override
    public String toString() {
        return "id:" + id;
    }

}
