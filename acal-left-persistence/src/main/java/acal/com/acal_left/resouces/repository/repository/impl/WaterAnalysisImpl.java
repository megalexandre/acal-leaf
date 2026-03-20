package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.core.model.WaterAnalysisItem;
import acal.com.acal_left.core.repository.WaterAnalysisRepository;
import acal.com.acal_left.resouces.repository.model.WaterAnalysisItemEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.WaterAnalysisJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WaterAnalysisImpl implements WaterAnalysisRepository {

    private final WaterAnalysisJpaRepository repository;

    public WaterAnalysisImpl(WaterAnalysisJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WaterAnalysis> findAll() {
        return repository.findAll().stream()
                .map(WaterAnalysisItemEntity::toDomain)
                .collect(Collectors.groupingBy(WaterAnalysisItem::getPeriod))
                .values()
                .stream()
                .map(WaterAnalysis::new)
                .sorted(Comparator.comparing(
                    (WaterAnalysis wa) -> wa.getAnalysis().getFirst().getPeriod()
                ).reversed())
                .toList();
    }

    @Override
    public List<WaterAnalysis> saveAll(List<WaterAnalysis> analyses) {
        return analyses;
    }
}
