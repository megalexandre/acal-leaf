package acal.com.acal_left.core.usecase.water;

import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.core.repository.WaterAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterAnalysisListUseCase {

    private final WaterAnalysisRepository repository;

    public WaterAnalysisListUseCase(WaterAnalysisRepository repository) {
        this.repository = repository;
    }

    public List<WaterAnalysis> execute() {
        return repository.findAll();
    }
}
