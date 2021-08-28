package com.leviatan.backend.repository;

import com.leviatan.backend.model.analysis.MatchAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchAnalysisRepository extends JpaRepository<MatchAnalysis, String> {
}
