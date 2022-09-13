package com.leviatan.backend.dto;

import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerWithMatches extends Player {

    private List<PlayedDto> matches;

    public PlayerWithMatches(Player player, List<Played> matches) {
        super();
        this.setId(player.getId());
        this.setPlayerId(player.getPlayerId());
        this.setSummonerName(player.getSummonerName());
        this.matches = matches.stream().map(PlayedDto::fromPlayed).collect(Collectors.toList());
    }
}
