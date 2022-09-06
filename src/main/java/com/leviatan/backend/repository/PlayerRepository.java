package com.leviatan.backend.repository;

import com.leviatan.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllByUser_Id(String userId);
    Optional<Player> findByIdAndUser_Id(String id, String userId);
    Optional<Player> findBySummonerNameAndUser_Id(String summonerName, String userId);
}
