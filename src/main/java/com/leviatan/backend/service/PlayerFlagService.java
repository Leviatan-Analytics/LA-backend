package com.leviatan.backend.service;

import com.leviatan.backend.model.*;
import com.leviatan.backend.repository.FlagRepository;
import com.leviatan.backend.repository.MatchRepository;
import com.leviatan.backend.repository.PlayerFlagRepository;
import com.leviatan.backend.repository.PlayerRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerFlagService {
    private final PlayerFlagRepository flagRepository;

    private final SessionUtils sessionUtils;

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerFlagService(PlayerFlagRepository flagRepository, SessionUtils sessionUtils, PlayerRepository playerRepository) {
        this.flagRepository = flagRepository;
        this.sessionUtils = sessionUtils;
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllFlagged() {
        User user = sessionUtils.getLoggedUserInfo();
        return flagRepository.findAllByUser(user).stream().map(PlayerFlag::getPlayer).collect(Collectors.toList());
    }

    public PlayerFlag createFlag(String playerId) {
        User user = sessionUtils.getLoggedUserInfo();
        Player player = playerRepository.getById(playerId);

        PlayerFlag flag = new PlayerFlag();
        flag.setUser(user);
        flag.setPlayer(player);

        return flagRepository.save(flag);
    }

    public void deleteFlag(String playerId) {
        User user = sessionUtils.getLoggedUserInfo();
        Player player = playerRepository.getById(playerId);

        Optional<PlayerFlag> flag = flagRepository.findByUserAndPlayer_Id(user, player.getPlayerId());
        flag.ifPresent(flagRepository::delete);
    }
}
