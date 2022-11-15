package com.leviatan.backend.repository;

import com.leviatan.backend.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Page<Player> findAllBySummonerNameContaining(String summonerName, Pageable pageable);
    Page<Player> findAllByIdInAndSummonerNameContaining(List<String> ids, String summonerName, Pageable pageable);
    Optional<Player> findByIdAndOrganization_Id(String id, String organizationId);
    Optional<Player> findBySummonerNameAndOrganization_Id(String summonerName, String organizationId);

    Optional<Player> findBySummonerName(String summonerName);
}
