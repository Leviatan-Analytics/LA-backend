package com.leviatan.backend.repository;

import com.leviatan.backend.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Page<Player> findAllByOrganization_IdAndSummonerNameContaining(String organizationId, String summonerName, Pageable pageable);
    Page<Player> findAllByOrganization_IdAndSummonerNameContainingAndPlayerFlag_User_Id(String organizationId, String summonerName, String userId, Pageable pageable);
    Optional<Player> findByIdAndOrganization_Id(String id, String organizationId);
    Optional<Player> findBySummonerNameAndOrganization_Id(String summonerName, String organizationId);

    Optional<Player> findBySummonerName(String summonerName);
}
