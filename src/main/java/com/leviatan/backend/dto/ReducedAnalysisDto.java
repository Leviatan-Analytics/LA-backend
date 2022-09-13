package com.leviatan.backend.dto;

import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReducedAnalysisDto {

    private String id;

    private LocalDateTime analysisDate;

    private LocalDateTime matchDate;

    private String matchId;

    private Long matchDuration;

    private String analysisType;

    private String redTeam;

    private String blueTeam;

    private String tournamentName;

    public static ReducedAnalysisDto from(ManualMatchAnalysis analysis) {
        return ReducedAnalysisDto.builder()
                .id(analysis.getId())
                .analysisDate(analysis.getAnalysisDate())
                .matchDate(analysis.getMatchDate())
                .matchId(analysis.getMatchId())
                .matchDuration(analysis.getMatchDuration())
                .analysisType("MANUAL_ANALYSIS")
                .blueTeam(analysis.getBlueSideAnalysis() != null ? analysis.getBlueSideAnalysis().getTeam() : null)
                .redTeam(analysis.getRedSideAnalysis() != null ? analysis.getRedSideAnalysis().getTeam() : null)
                .tournamentName(analysis.getTournamentName())
                .build();
    }

    public static ReducedAnalysisDto from(MatchAnalysis analysis) {
        return ReducedAnalysisDto.builder()
                .id(analysis.getId())
                .analysisDate(analysis.getAnalysisDate())
                .matchDate(analysis.getMatchDate())
                .matchId(analysis.getMatchId())
                .matchDuration(analysis.getMatchDuration())
                .analysisType("REGULAR_ANALYSIS")
                .build();
    }
}
