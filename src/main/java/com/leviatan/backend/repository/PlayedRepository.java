package com.leviatan.backend.repository;

import com.leviatan.backend.model.Played;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayedRepository extends JpaRepository<Played, String> {
    List<Played> getAllByPlayer_Id(String playerId);

    List<Played> getAllByMatch_Id(String matchId);

    Played getByPlayer_IdAndMatch_Id(String playerId, String matchId);
}
