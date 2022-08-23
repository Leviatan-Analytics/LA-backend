package com.leviatan.backend.service;

import com.leviatan.backend.dto.PlayerWithMatches;
import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.Player;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.PlayedRepository;
import com.leviatan.backend.repository.PlayerRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayedRepository playedRepository;
    private final SessionUtils sessionUtils;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayedRepository playedRepository, SessionUtils sessionUtils) {
        this.playerRepository = playerRepository;
        this.playedRepository = playedRepository;
        this.sessionUtils = sessionUtils;
    }

    public List<Player> getAllPlayers() {
        User user = sessionUtils.getLoggedUserInfo();
        return playerRepository.findAllByUser_Id(user.getId());
    }

    public PlayerWithMatches getPlayerWithMatches(String playerId, String position, String team, String champion) {
        User user = sessionUtils.getLoggedUserInfo();
        Player player = playerRepository.findByIdAndUser_Id(playerId, user.getId()).orElse(null);
        if (player == null) return null;
        List<Played> played = playedRepository.getAllByPlayer_Id(player.getId());
        return new PlayerWithMatches(
            player,
            played.stream().filter(p -> {
                if(position != null && !position.equals(p.getPosition().toString())) return false;
                if(team != null && !team.equals(p.getTeam().toString())) return false;
                return champion == null || champion.equals(p.getChampion().getName());
            }).collect(Collectors.toList())
        );
    }

    public Player getPlayerProfile(String playerName) {
        User user = sessionUtils.getLoggedUserInfo();
        return playerRepository.findBySummonerName(playerName, user.getId()).orElse(null);
    }
}
