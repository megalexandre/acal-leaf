package acal.com.acal_left.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "conta")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "id_endereco_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonAddresses personAddresses;

    public boolean isPartnerExclusive() {
        return personAddresses.isPartnerExclusive();
    }

    public boolean isNormalPartner() {
        return !personAddresses.isPartnerExclusive();
    }

    /*
    @Column(name = "dataGerada")
    private Timestamp data;

    @Column(name = "dataPag")
    private Timestamp pagamento;

    @Column(name = "dataVence")
    private Timestamp vencimento;

    @Column(name = "MesReferente")
    private String MesReferente;

    @Column(name = "AnoReferente")
    private Long AnoReferente;

    @Column(name = "nome_socio")
    private String socio;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private String numero;

    @Column(name = "numeroSocio")
    private Integer numeroSocio;

    @Column(name = "categoriaSocio")
    private String categoriaSocio;

    @Column(name = "ValorTaxa")
    private java.math.BigDecimal taxaSocio;

    @Column(name = "Consumo")
    private Double consumo;

    @Column(name = "excessoLTd")
    private Double excessoLTd;

    @Column(name = "excessoValor")
    private Double excessoValor;

    @Column(name = "taxas")
    private Long taxas;

    @Column(name = "totalconta")
    private Double totalconta;
     */

}
