package com.leviatan.backend.dto;

import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerWithMatches extends Player {

    private List<Played> matches;

    public PlayerWithMatches(Player player, List<Played> matches) {
        super();
        this.setId(player.getId());
        this.setPlayerId(player.getPlayerId());
        this.setSummonerName(player.getSummonerName());
        this.setUser(player.getUser());
        this.matches = matches;
    }
}
