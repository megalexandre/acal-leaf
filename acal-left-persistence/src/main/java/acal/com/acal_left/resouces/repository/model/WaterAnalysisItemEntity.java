package acal.com.acal_left.resouces.repository.model;

import acal.com.acal_left.core.model.WaterAnalysisItem;
import acal.com.acal_left.shared.model.WaterParameterType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "parametro_coleta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaterAnalysisItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ide_parametro_coleta")
    private Integer id;

    @Column(name = "ide_tipo_parametro", nullable = false)
    private Integer parameterTypeId;

    @Basic(optional = false)
    @Column(name = "exigido")
    private String required;

    @Basic(optional = false)
    @Column(name = "analisado")
    private String analyzed;

    @Basic(optional = false)
    @Column(name = "conformidade")
    private String conformity;

    @Basic(optional = false)
    @Column(name = "data")
    private LocalDate period;

    public static WaterAnalysisItemEntity toEntity(WaterAnalysisItem item) {
        return WaterAnalysisItemEntity.builder()
                .id(item.getId())
                .parameterTypeId(item.getType().getId())
                .required(item.getRequired())
                .analyzed(item.getAnalyzed())
                .conformity(item.getConformity())
                .period(item.getPeriod())
                .build();
    }

    public static WaterAnalysisItem toDomain(WaterAnalysisItemEntity entity) {
        return WaterAnalysisItem.builder()
                .id(entity.getId())
                .type(WaterParameterType.fromId(entity.parameterTypeId))
                .required(entity.getRequired())
                .analyzed(entity.getAnalyzed())
                .conformity(entity.getConformity())
                .period(entity.getPeriod())
                .build();
    }


    @Override
    public String toString() {
        return "id:" + id;
    }
}
