package com.leviatan.backend.repository;

import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.league.Position;
import com.leviatan.backend.model.league.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayedRepository extends JpaRepository<Played, String> {
    List<Played> getAllByPlayer_Id(String playerId);

    Played getByPlayer_IdAndMatch_Id(String playerId, String matchId);
}
