package com.leviatan.backend.model.manual_analysis;

import com.leviatan.backend.model.manual_analysis.player.PlayerInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class MatchInfo implements Serializable {
    private List<PlayerInfo> blueTeamPlayers;
    private List<PlayerInfo> redTeamPlayers;
}
