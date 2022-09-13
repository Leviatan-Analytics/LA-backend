package com.leviatan.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.league.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {
    private String matchId;
    private String id;
    private Region region;
    private String tournamentName;
    private Long duration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime matchDate;

    public static MatchDto fromMatch(Match match) {
        return MatchDto.builder()
                .id(match.getId())
                .matchId(match.getMatchId())
                .region(match.getRegion())
                .tournamentName(match.getTournamentName())
                .duration(match.getDuration())
                .matchDate(match.getMatchDate())
                .build();
    }
}
