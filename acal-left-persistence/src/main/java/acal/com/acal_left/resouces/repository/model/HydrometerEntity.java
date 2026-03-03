package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "hidrometro")
@Data
public class HydrometerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhidrometro")
    private Integer id;

    @Column(name = "Consumo")
    private Double usage;

}
