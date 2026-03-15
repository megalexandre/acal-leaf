package acal.com.acal_left.resouces.repository.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "hidrometro")
@Data
public class HydrometerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hydrometer-gen")
    @TableGenerator(name = "hydrometer-gen", table = "id_sequences", pkColumnName = "seq_name",
            valueColumnName = "seq_value", pkColumnValue = "hidrometro_id", allocationSize = 50)
    @Basic(optional = false)
    @Column(name = "idhidrometro")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idconta", referencedColumnName = "id")
    private InvoiceEntity entity;

    @Column(name = "consumo_inicial")
    private Double consumptionStart;

    @Column(name = "consumo_final")
    private Double consumptionEnd;

    @Override
    public String toString() {
        return "id:" + id;
    }
}
