package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "enderecopessoa")
@Data
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero")
    private String number;

    @Basic(optional = false)
    @Column(name = "inativo")
    private boolean inactive;

    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AddressEntity address;

    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonEntity person;

    @JoinColumn(name = "id_categoria_socio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryEntity category;

    @Override
    public String toString() {
        return "id:" + id;
    }
}
