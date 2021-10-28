package com.leviatan.backend.model.manual_analysis;

import com.leviatan.backend.model.analysis.position.PlayerPosition;
import com.leviatan.backend.model.manual_analysis.bushes.BushInfo;
import com.leviatan.backend.model.manual_analysis.jungle.JunglePath;
import com.leviatan.backend.model.manual_analysis.objectives.ObjectiveInfo;
import com.leviatan.backend.model.manual_analysis.player.PlayerInfo;
import com.leviatan.backend.model.manual_analysis.structures.StructureInfo;
import com.leviatan.backend.model.manual_analysis.wards.WardPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManualMatchSideAnalysis implements Serializable {

    private String team;

    private List<PlayerInfo> players;

    private List<WardPosition> wards;

    private List<PlayerPosition> positions;

    private List<BushInfo> bushes;

    private List<String> picks;

    private List<String> bans;

    private List<StructureInfo> structures;

    private JunglePath junglePath;

    private List<ObjectiveInfo> objectives;
}
