package com.leviatan.backend.repository;
import com.leviatan.backend.model.Flag;
import com.leviatan.backend.model.PlayerFlag;
import com.leviatan.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerFlagRepository extends JpaRepository<PlayerFlag, String> {
    List<PlayerFlag> findAllByUser(User user);
    Optional<PlayerFlag> findByUserAndPlayer_Id(User user, String playerId);
}
