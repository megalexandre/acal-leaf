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
    private Integer id;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PartnerEntity partner;

    @Basic(optional = false)
    @Column(name = "nome")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;


    public String getDocument() {
        if (cpf != null && !cpf.isEmpty()) {
            return cpf;

        } else if (cnpj != null && !cnpj.isEmpty()) {
            return cnpj;
        }

        return null;
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}