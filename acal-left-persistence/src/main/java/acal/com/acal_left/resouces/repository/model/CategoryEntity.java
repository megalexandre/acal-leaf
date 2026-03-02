package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.shared.model.MemberGroup;
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
    private BigDecimal amountWater;

    @Column(name = "amount_partner", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountPartner;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_id", nullable = false)
    private MemberGroup memberGroup;

}

