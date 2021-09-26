package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.model.analysis.position.PlayerPosition;
import com.leviatan.backend.model.manual_analysis.ManualMatchSideAnalysis;
import com.leviatan.backend.model.manual_analysis.MatchInfo;
import com.leviatan.backend.model.manual_analysis.bushes.BushInfo;
import com.leviatan.backend.model.manual_analysis.objectives.ObjectiveInfo;
import com.leviatan.backend.model.manual_analysis.structures.StructureInfo;
import com.leviatan.backend.model.manual_analysis.wards.WardPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchAnalysisResult {
    private MatchInfo matchInfo;
    private List<WardPosition> wards;
    private List<PlayerPosition> positions;
    private List<BushInfo> bushes;
    private List<String> picks;
    private List<String> bans;
    private List<StructureInfo> structures;
    private List<ObjectiveInfo> objectives;

    public static MatchAnalysisResult from(ManualMatchSideAnalysis analysisSide, MatchInfo matchInfo) {
        return MatchAnalysisResult.builder()
                .matchInfo(matchInfo)
                .wards(analysisSide.getWards())
                .positions(analysisSide.getPositions())
                .bushes(analysisSide.getBushes())
                .picks(analysisSide.getPicks())
                .bans(analysisSide.getBans())
                .structures(analysisSide.getStructures())
                .objectives(analysisSide.getObjectives())
                .build();
    }


}
