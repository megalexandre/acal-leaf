package acal.com.acal_left.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "socio")
@Data
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Person person;

    public String getName() {
        return person.getName();
    }

}
