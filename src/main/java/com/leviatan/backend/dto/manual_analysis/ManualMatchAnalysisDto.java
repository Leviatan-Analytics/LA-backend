package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.model.manual_analysis.ManualMatchSideAnalysis;
import com.leviatan.backend.model.manual_analysis.MatchInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualMatchAnalysisDto {

    private MatchInfo matchInfo;

    private ManualMatchSideAnalysis redSideAnalysis;

    private ManualMatchSideAnalysis blueSideAnalysis;
}
