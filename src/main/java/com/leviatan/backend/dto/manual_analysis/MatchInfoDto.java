package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.model.manual_analysis.player.PlayerInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MatchInfoDto {

    private String matchId;
    private LocalDateTime matchDate;
    private List<PlayerInfo> blueTeamPlayers;
    private List<PlayerInfo> redTeamPlayers;
    private Long matchDuration;
}
