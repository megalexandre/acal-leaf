package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.resouces.repository.repository.impl.AddressRepositoryImpl;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    public static LinkEntity toEntity(Link link) {
        LinkEntity entity = new LinkEntity();
        entity.setId(link.getId());
        entity.setNumber(link.getNumber());
        entity.setInactive(!link.getActive());
        entity.setAddress(AddressRepositoryImpl.toModel(link.getAddress()));
        entity.setCategory(CategoryEntity.fromEntity(link.getCategory()));
        entity.setPerson(PersonEntity.fromEntity(link.getPerson()));
        return entity;
    }

    public static Link toEntity(LinkEntity entity) {
        return Link.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .active(!entity.isInactive())
                .address(AddressRepositoryImpl.toEntity(entity.getAddress()))
                .category(CategoryEntity.toEntity(entity.getCategory()))
                .person(PersonEntity.toEntity(entity.getPerson()))
                .build();
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}
