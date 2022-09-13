package com.leviatan.backend.dto;

import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.Score;
import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Position;
import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayedDto {
    private String id;
    private Team team;
    private Position position;
    private Champion champion;
    private Integer gold;
    private Integer cs;
    private Integer vision;
    private Score score;
    private MatchDto match;

    public static PlayedDto fromPlayed(Played played) {
        return PlayedDto.builder()
                .id(played.getId())
                .team(played.getTeam())
                .position(played.getPosition())
                .champion(played.getChampion())
                .gold(played.getGold())
                .cs(played.getCs())
                .vision(played.getVision())
                .score(played.getScore())
                .match(MatchDto.fromMatch(played.getMatch()))
                .build();
    }
}
