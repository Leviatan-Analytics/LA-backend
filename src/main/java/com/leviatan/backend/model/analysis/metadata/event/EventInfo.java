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
    private String eventId;
    private String eventName;
    private Double eventTime;

    private String acer;
    private String acingTeam;

    private List<String> assisters;
    private String killerName;
    private String victimName;

    private String inhibKilled;

    private Integer killStreak;

    private String turretKilled;

}
