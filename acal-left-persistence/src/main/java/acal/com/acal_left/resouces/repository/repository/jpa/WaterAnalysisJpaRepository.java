package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.WaterAnalysisItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WaterAnalysisJpaRepository extends JpaRepository<WaterAnalysisItemEntity, Integer> {

    List<WaterAnalysisItemEntity> findByPeriodIn(List<LocalDate> period);
}
