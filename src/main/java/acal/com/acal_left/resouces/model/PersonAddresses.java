package acal.com.acal_left.resouces.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "enderecopessoa")
@Data
public class PersonAddresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero")
    private String number;

    @Basic(optional = false)
    @Column(name = "inativo")
    private boolean inactive;

    @Basic(optional = false)
    @Column(name = "socio_exclusivo")
    private boolean partnerExclusive;

    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Addresses address;

    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person person;

    @JoinColumn(name = "id_categoria_socio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryModel categoryModel;

}
