package com.leviatan.backend.model.manual_analysis;

import com.leviatan.backend.model.manual_analysis.player.PlayerInfo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MatchInfo implements Serializable {
    private String matchId;
    private LocalDateTime matchDate;
    private List<PlayerInfo> blueTeamPlayers;
    private List<PlayerInfo> redTeamPlayers;
}
