package com.leviatan.backend.model.analysis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.PlayerFrameMetadata;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.Organization;
import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.analysis.metadata.PlayerMetadata;
import com.leviatan.backend.model.analysis.metadata.event.EventInfo;
import com.leviatan.backend.model.analysis.position.MatchFrameInfo;
import com.leviatan.backend.dto.ReducedAnalysisDto;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchAnalysis extends Analysis {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime analysisDate;

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

    public static MatchAnalysis from(MatchAnalysisDto matchAnalysisDto, Organization organization, String savedMatchId) {
        MatchAnalysis analysis = MatchAnalysis.builder()
                .analysisDate(matchAnalysisDto.getAnalysisDate())
                .players(matchAnalysisDto.getPlayers())
                .frames(matchAnalysisDto.getFrames())
                .events(matchAnalysisDto.getEvents())
                .framesMetadata(matchAnalysisDto.getFramesMetadata())
                .build();
        analysis.setMatchId(savedMatchId);
        analysis.setMatchDate(matchAnalysisDto.getMatchDate());
        analysis.setMatchDuration(matchAnalysisDto.getMatchDuration());
        analysis.setOrganization(organization);
        return analysis;
    }

    public static MatchAnalysis from(MatchAnalysis matchAnalysis, List<Played> played) {
        MatchAnalysis analysis = MatchAnalysis.builder()
                .analysisDate(matchAnalysis.getAnalysisDate())
                .players(matchAnalysis.getPlayers())
                .frames(matchAnalysis.getFrames())
                .events(matchAnalysis.getEvents())
                .framesMetadata(matchAnalysis.getFramesMetadata())
                .build();
        analysis.setMatchId(matchAnalysis.getMatchId());
        analysis.setMatchDate(matchAnalysis.getMatchDate());
        analysis.setMatchDuration(matchAnalysis.getMatchDuration());
        analysis.setOrganization(matchAnalysis.getOrganization());
        analysis.setPlayers(played.stream().map(PlayerMetadata::from).collect(Collectors.toList()));
        return analysis;
    }

    @Override
    public ReducedAnalysisDto toReducedDto() {
        return ReducedAnalysisDto.from(this);
    }
}
