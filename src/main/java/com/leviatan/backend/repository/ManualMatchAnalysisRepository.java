package com.leviatan.backend.repository;

import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualMatchAnalysisRepository extends JpaRepository<ManualMatchAnalysis, String> {
}
