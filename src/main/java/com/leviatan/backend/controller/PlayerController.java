package com.leviatan.backend.controller;

import com.leviatan.backend.dto.PlayerDto;
import com.leviatan.backend.dto.PlayerWithMatches;
import com.leviatan.backend.model.Player;
import com.leviatan.backend.model.PlayerFlag;
import com.leviatan.backend.service.PlayerFlagService;
import com.leviatan.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
@CrossOrigin(origins = "*")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerFlagService playerFlagService;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerFlagService playerFlagService) {
        this.playerService = playerService;
        this.playerFlagService = playerFlagService;
    }

    @GetMapping()
    public Page<PlayerDto> getAllPlayers(
            @RequestParam Optional<String> playerName,
            @RequestParam Optional<Boolean> flagged,
            @RequestParam Optional<Integer> page
    ) {
       return playerService.getAllPlayers(playerName.orElse(""), flagged.orElse(false), page.orElse(0));
    }

    @PostMapping("/{playerId}/flag")
    public PlayerFlag flagPlayer(@PathVariable String playerId) {
        return playerFlagService.createFlag(playerId);
    }

    @DeleteMapping("/{playerId}/flag")
    public void unFlagPlayer(@PathVariable String playerId) {
        playerFlagService.deleteFlag(playerId);
    }

    @GetMapping("/{playerId}")
    public PlayerWithMatches getPlayerInfoWithMatches(
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String champion,
            @PathVariable String playerId) {
        return playerService.getPlayerWithMatches(playerId, position, team, champion);
    }

    @GetMapping("/{playerName}/profile")
    public Player getPlayerProfile(@PathVariable String playerName) {
        return playerService.getPlayerProfile(playerName);
    }

}
