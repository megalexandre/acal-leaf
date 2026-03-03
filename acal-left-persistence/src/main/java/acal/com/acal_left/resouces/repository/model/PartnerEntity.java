package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "socio")
@Data
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private PersonEntity person;


}
