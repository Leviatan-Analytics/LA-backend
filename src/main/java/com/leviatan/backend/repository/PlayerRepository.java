package com.leviatan.backend.repository;

import com.leviatan.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllByOrganization_Id(String organizationId);
    Optional<Player> findByIdAndOrganization_Id(String id, String organizationId);
    Optional<Player> findBySummonerNameAndOrganization_Id(String summonerName, String organizationId);

    Optional<Player> findBySummonerName(String summonerName);
}
