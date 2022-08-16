package com.leviatan.backend.model.manual_analysis.player;

import com.leviatan.backend.model.league.Team;
import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerInfo implements Serializable {
    private Team team;
    private String summonerName;
    private String championId;
    private String spellOne;
    private String spellTwo;
    private String role;
}
