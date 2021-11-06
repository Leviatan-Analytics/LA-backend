package com.leviatan.backend.repository;

import com.leviatan.backend.model.Analysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AnalysisRepository extends JpaRepository<Analysis, String> {

    @Query(nativeQuery = true, value = "select * from analysis a where a.user_id = ?1")
    Page<Analysis> findAllAnalysesPaginated(String userId, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from analysis a where a.user_id = ?1 and a.dtype = ?2")
    Page<Analysis> findAnalysesPaginated(String userId, String analysisType, Pageable pageable);

    @Transactional
    @Modifying
    @Query("delete from Analysis a where a.id = ?1 and a.user.id = ?2")
    void deleteAnalysisByIdAndUser_Id(String analysisId, String userId);
}
