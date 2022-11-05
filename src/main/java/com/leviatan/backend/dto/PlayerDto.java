package com.leviatan.backend.dto;

import com.leviatan.backend.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {
    private String id;
    private String playerId;
    private String summonerName;
    private Boolean flagged;

    public static PlayerDto fromPlayerAndUserId(Player player, String userId) {
        return PlayerDto.builder()
                .id(player.getId())
                .playerId(player.getPlayerId())
                .summonerName(player.getSummonerName())
                .flagged(player.getPlayerFlag()
                        .stream().anyMatch(playerFlag -> playerFlag.getUser().getId().equals(userId)))
                .build();
    }
}
