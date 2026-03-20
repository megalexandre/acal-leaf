package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.WaterAnalysis;

import java.util.List;

public interface WaterAnalysisRepository {

    List<WaterAnalysis> findAll();
    List<WaterAnalysis> saveAll(List<WaterAnalysis> analyses);
}
