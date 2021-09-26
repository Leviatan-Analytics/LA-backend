package com.leviatan.backend.model.manual_analysis.objectives;

import com.leviatan.backend.model.analysis.position.PlayerPosition;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ObjectiveInfo implements Serializable {
    private String objectiveId;
    private Integer objectiveNumber;
    private Double timestamp;
    private PushStatus upperLane;
    private PushStatus lowerLane;
    private List<PlayerPosition> nearPlayers;
    private String objectiveKiller;
}
