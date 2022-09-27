package com.leviatan.backend.model.analysis.metadata;

import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.league.Team;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerMetadata implements Serializable {
    private String championName;
    private String playerId;
    private Team team;
    private List<ItemInfo> items;
    private Integer level;
    private String position;
    private RuneInfo runes;
    private ScoreInfo score;
    private String summonerName;
    private SpellInfo spells;

    public static PlayerMetadata from(Played played) {
        return PlayerMetadata.builder()
                .championName(played.getChampion().getName())
                .team(played.getTeam())
                .playerId(played.getPlayer().getId())
                .position(played.getPosition().name())
                .score(ScoreInfo.from(played.getScore(), played.getCs(), played.getVision()))
                .summonerName(played.getPlayer().getSummonerName())
                .build();
    }
}
