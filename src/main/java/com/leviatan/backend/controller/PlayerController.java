package com.leviatan.backend.controller;

import com.leviatan.backend.model.Player;
import com.leviatan.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@CrossOrigin(origins = "*")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
       return playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public Player getPlayerInfoWithMatches(
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
