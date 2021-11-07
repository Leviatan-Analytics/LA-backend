package com.leviatan.backend.model.analysis.metadata.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventInfo implements Serializable {
    private String EventId;
    private String EventName;
    private Double EventTime;

    private String Acer;
    private String AcingTeam;

    private List<String> Assisters;
    private String KillerName;
    private String VictimName;

    private String InhibKilled;

    private Integer KillStreak;

    private String TurretKilled;

}
