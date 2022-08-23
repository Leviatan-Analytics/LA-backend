package com.leviatan.backend.repository;

import com.leviatan.backend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, String> {
    Optional<Match> findByIdAndUser_Id(String id, String userId);
}
