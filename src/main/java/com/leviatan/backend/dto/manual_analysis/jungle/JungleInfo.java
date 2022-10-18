package com.leviatan.backend.dto.manual_analysis.jungle;

import com.leviatan.backend.dto.manual_analysis.LanePriority;
import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JungleInfo {

    private Team side;
    private Double botToTopPercentage;
    private Double TopToBotPercentage;
    private LanePriority firstGank;
    private LanePriority jungleProximity;
}
