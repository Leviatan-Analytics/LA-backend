package com.leviatan.backend.model.analysis.metadata.event;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampionKillEvent extends EventInfo implements Serializable {
    private List<String> assisters;
    private String killerName;
    private String victimName;
}
