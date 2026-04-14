package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.Document;
import acal.com.acal_left.core.model.Person;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Column(name = "partner_number")
    private String partnerNumber;

    @Basic(optional = false)
    private String name;

    private String cpf;

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
                .partnerNumber(personEntity.getPartnerNumber())
                .document(Document.builder().value(personEntity.getDocument()).build())
                .build();
    }

    public static PersonEntity fromEntity(Person person) {
        PersonEntity entity = PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .partnerNumber(person.getPartnerNumber())
                .build();

        if (person.getDocument() != null) {
            String documentValue = person.getDocument().getValue();
            if (documentValue != null) {
                if (documentValue.length() == 11) {
                    entity.setCpf(documentValue);
                } else if (documentValue.length() == 14) {
                    entity.setCnpj(documentValue);
                }
            }
        }

        return entity;
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}