package com.leviatan.backend.dto;

import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.analysis.MatchPlayerInfo;
import com.leviatan.backend.model.analysis.position.MatchFrameInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchAnalysisDto {

    @NotNull
    private LocalDateTime analysisDate;

    @NotNull
    private LocalDateTime matchDate;

    @NotNull
    private String matchId;

    @NotNull
    private Long matchDuration;

    @NotNull
    @NotEmpty
    @Size(min = 10)
    private List<MatchPlayerInfo> players;

    @NotNull
    @NotEmpty
    private List<MatchFrameInfo> frames;

    public static MatchAnalysisDto from(MatchAnalysis matchAnalysis) {
        return MatchAnalysisDto.builder()
                .analysisDate(matchAnalysis.getAnalysisDate())
                .matchDate(matchAnalysis.getMatchDate())
                .matchId(matchAnalysis.getMatchId())
                .matchDuration(matchAnalysis.getMatchDuration())
                .players(matchAnalysis.getPlayers())
                .frames(matchAnalysis.getFrames())
                .build();
    }
}
