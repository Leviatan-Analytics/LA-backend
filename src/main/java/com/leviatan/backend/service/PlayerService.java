package com.leviatan.backend.service;

import com.leviatan.backend.dto.PlayerDto;
import com.leviatan.backend.dto.PlayerWithMatches;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.Player;
import com.leviatan.backend.model.PlayerFlag;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.PlayedRepository;
import com.leviatan.backend.repository.PlayerFlagRepository;
import com.leviatan.backend.repository.PlayerRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Page<PlayerDto> getAllPlayers(String playerName, Boolean flagged, List<String> flaggedIds, Integer page) {
        User user = sessionUtils.getLoggedUserInfo();
        if (flagged) {
            Page<Player> playerPage = playerRepository.findAllByIdInAndSummonerNameContaining(
                    flaggedIds,
                    playerName,
                    PageRequest.of(page, 10));
            return playerPage.map(player ->
                            PlayerDto.fromPlayerAndUserId(player, user.getId(), flaggedIds.contains(player.getId()))
                    );
        } else {
            return playerRepository.findAllBySummonerNameContaining(
                            playerName,
                            PageRequest.of(page, 10))
                    .map(player ->
                            PlayerDto.fromPlayerAndUserId(player, user.getId(), flaggedIds.contains(player.getId()))
                    );
        }
    }

    public PlayerWithMatches getPlayerWithMatches(String playerId, String position, String team, String champion) {
        User user = sessionUtils.getLoggedUserInfo();
        Player player = playerRepository.findByIdAndOrganization_Id(playerId, user.getOrganization().getId()).orElseThrow(() -> new NotFoundException("Player not found"));
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
        return playerRepository.findBySummonerNameAndOrganization_Id(playerName, user.getOrganization().getId()).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Player findBySummonerName(String summonerName) {
        return playerRepository.findBySummonerName(summonerName).orElse(null);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
