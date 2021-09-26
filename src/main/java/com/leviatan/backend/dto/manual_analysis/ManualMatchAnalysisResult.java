package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.dto.manual_analysis.jungle.InvadeInfo;
import com.leviatan.backend.dto.manual_analysis.jungle.JungleInfo;
import com.leviatan.backend.dto.manual_analysis.jungle.JunglePathResult;
import com.leviatan.backend.dto.manual_analysis.player.BansCount;
import com.leviatan.backend.dto.manual_analysis.player.PicksCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManualMatchAnalysisResult {
    private List<MatchAnalysisResult> matches;
    private List<TrinketChangeInfo> trinketChanges;
    private List<BushPercentage> bushInfo;
    private List<InvadeInfo> invadeInfos;
    private List<PicksCount> picksInfo;
    private List<PicksCount> rivalsPicksInfo;
    private List<BansCount> bansInfo;
    private List<BansCount> rivalBansInfo;
    private StructuresPriority structuresInfo;
    private List<JunglePathResult> junglePaths;
    private List<JungleInfo> jungleInfos;
    private List<ObjectiveResult> objectivesInfo;

}
