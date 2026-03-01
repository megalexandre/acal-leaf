package acal.com.acal_left.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoriasocio")
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String name;

}
