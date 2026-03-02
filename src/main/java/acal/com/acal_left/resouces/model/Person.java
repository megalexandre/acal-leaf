package acal.com.acal_left.resouces.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Partner partner;

    @Basic(optional = false)
    @Column(name = "nome")
    private String name;

    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    @Column(name = "apelido")
    private String nickname;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "uf")
    private String uf;


}
