package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.model.analysis.Team;
import com.leviatan.backend.model.manual_analysis.objectives.PushStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectiveResult {

    private Team side;
    private String objectiveId;
    private Integer objectiveNumber;
    private PushStatus upperLane;
    private PushStatus lowerLane;
    private Double winPercentage;
    private Double skirmishWinPercentage;
}
