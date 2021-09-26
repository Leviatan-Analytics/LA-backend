package com.leviatan.backend.dto.manual_analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructuresPriority {
    private LanePriority firstTowerPriority;
    private LanePriority secondTowerPriority;
    private List<TowerSequence> towerSequences;
}
