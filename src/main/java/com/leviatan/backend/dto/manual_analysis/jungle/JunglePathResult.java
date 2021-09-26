package com.leviatan.backend.dto.manual_analysis.jungle;

import com.leviatan.backend.dto.manual_analysis.Matchup;
import com.leviatan.backend.model.analysis.Team;

import java.util.List;

public class JunglePathResult {
    private Team side;
    private List<Matchup> matchups;
    private List<JunglePosition> path;
    private LeashInfo leashInfo;
}
