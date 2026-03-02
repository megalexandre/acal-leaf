package acal.com.acal_left.resouces.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_model")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;
}
