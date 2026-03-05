package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.Document;
import acal.com.acal_left.core.model.Partner;
import acal.com.acal_left.core.model.Person;
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

    public static Person toEntity(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .partner(
                        Partner.builder()
                                .build()
                )
                .document(Document.builder().value(personEntity.getDocument()).build())
                .build();
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}