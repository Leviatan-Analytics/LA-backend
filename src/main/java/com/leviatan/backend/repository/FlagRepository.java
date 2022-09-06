package com.leviatan.backend.repository;

import com.leviatan.backend.model.Flag;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlagRepository extends JpaRepository<Flag, String> {
    Optional<Flag> findByUserAndMatch_Id(User user, String matchId);
}
