package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "taxa")
@Data
public class RateEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String name;

    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal amount;

    @Override
    public String toString() {
        return "id:" + id;
    }

}
