package com.leviatan.backend.model.analysis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.PlayerFrameMetadata;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.Organization;
import com.leviatan.backend.model.analysis.metadata.PlayerMetadata;
import com.leviatan.backend.model.analysis.metadata.event.EventInfo;
import com.leviatan.backend.model.analysis.position.MatchFrameInfo;
import com.leviatan.backend.dto.ReducedAnalysisDto;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchAnalysis extends Analysis {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime analysisDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime matchDate;

    private String matchId;

    private Long matchDuration;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private List<PlayerMetadata> players;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private List<MatchFrameInfo> frames;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private List<EventInfo> events;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private List<PlayerFrameMetadata> framesMetadata;

    public static MatchAnalysis from(MatchAnalysisDto matchAnalysisDto, Organization organization) {
        MatchAnalysis analysis = MatchAnalysis.builder()
                .analysisDate(matchAnalysisDto.getAnalysisDate())
                .matchDate(matchAnalysisDto.getMatchDate())
                .matchId(matchAnalysisDto.getMatchId())
                .matchDuration(matchAnalysisDto.getMatchDuration())
                .players(matchAnalysisDto.getPlayers())
                .frames(matchAnalysisDto.getFrames())
                .events(matchAnalysisDto.getEvents())
                .framesMetadata(matchAnalysisDto.getFramesMetadata())
                .build();
        analysis.setOrganization(organization);
        return analysis;
    }

    @Override
    public ReducedAnalysisDto toReducedDto() {
        return ReducedAnalysisDto.from(this);
    }
}
