package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.model.analysis.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TowerSequence {

    private String firstTower;
    private String secondTower;
    private Double percentage;
    private List<String> rivals;
    private Team side;
}
