package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PartnerEntity partner;

    @Basic(optional = false)
    @Column(name = "nome")
    private String name;

}