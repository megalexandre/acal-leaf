package acal.com.acal_left.resouces.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount_water", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountWater = BigDecimal.ZERO;

    @Column(name = "amount_partner", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountPartner = BigDecimal.ZERO;

    @Column(name = "group_id", nullable = false)
    private Group group;

}

