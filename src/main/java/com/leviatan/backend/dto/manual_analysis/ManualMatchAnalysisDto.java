package com.leviatan.backend.dto.manual_analysis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.model.manual_analysis.ManualMatchSideAnalysis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualMatchAnalysisDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime analysisDate;

    private MatchInfoDto matchInfo;

    private ManualMatchSideAnalysis redSideAnalysis;

    private ManualMatchSideAnalysis blueSideAnalysis;
}
