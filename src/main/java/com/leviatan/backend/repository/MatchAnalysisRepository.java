package com.leviatan.backend.repository;

import com.leviatan.backend.model.analysis.MatchAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchAnalysisRepository extends JpaRepository<MatchAnalysis, String> {
    List<MatchAnalysis> findAllByOrganization_Id(String organizationId);
}
