package acal.com.acal_left.core.usecase.water;

import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.core.repository.WaterAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterAnalysisSaveUseCase {

    private final WaterAnalysisRepository repository;

    public WaterAnalysisSaveUseCase(WaterAnalysisRepository repository) {
        this.repository = repository;
    }

    public List<WaterAnalysis> execute(List<WaterAnalysis> waterAnalyses) {
        return repository.saveAll(waterAnalyses);
    }
}
