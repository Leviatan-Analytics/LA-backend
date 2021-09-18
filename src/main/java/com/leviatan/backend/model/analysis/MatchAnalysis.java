package com.leviatan.backend.model.analysis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.model.UUIDEntity;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.analysis.metadata.PlayerMetadata;
import com.leviatan.backend.model.analysis.position.MatchFrameInfo;
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
public class MatchAnalysis extends UUIDEntity {
    @ManyToOne
    private User user;

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

    public static MatchAnalysis from(MatchAnalysisDto matchAnalysis, User user) {
        return MatchAnalysis.builder()
                .user(user)
                .analysisDate(matchAnalysis.getAnalysisDate())
                .matchDate(matchAnalysis.getMatchDate())
                .matchId(matchAnalysis.getMatchId())
                .matchDuration(matchAnalysis.getMatchDuration())
                .players(matchAnalysis.getPlayers())
                .frames(matchAnalysis.getFrames())
                .build();
    }
}
