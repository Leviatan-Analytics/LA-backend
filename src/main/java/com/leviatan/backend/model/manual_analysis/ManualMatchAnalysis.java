package com.leviatan.backend.model.manual_analysis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualMatchAnalysis extends Analysis {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime analysisDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime matchDate;

    private String matchId;

    private Long matchDuration;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private MatchInfo matchPlayers;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private ManualMatchSideAnalysis redSideAnalysis;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private ManualMatchSideAnalysis blueSideAnalysis;


    public static ManualMatchAnalysis from(ManualMatchAnalysisDto matchAnalysis, User user) {
        ManualMatchAnalysis analysis = ManualMatchAnalysis.builder()
                .analysisDate(matchAnalysis.getAnalysisDate() != null ? matchAnalysis.getAnalysisDate() : LocalDateTime.now())
                .matchDate(matchAnalysis.getMatchInfo().getMatchDate())
                .matchId(matchAnalysis.getMatchInfo().getMatchId())
                .matchDuration(matchAnalysis.getMatchInfo().getMatchDuration())
                .matchPlayers(new MatchInfo(matchAnalysis.getMatchInfo().getBlueTeamPlayers(), matchAnalysis.getMatchInfo().getRedTeamPlayers()))
                .redSideAnalysis(matchAnalysis.getRedSideAnalysis())
                .blueSideAnalysis(matchAnalysis.getBlueSideAnalysis())
                .build();
        analysis.setUser(user);
        return analysis;
    }
}
