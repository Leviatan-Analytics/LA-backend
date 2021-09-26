package com.leviatan.backend.model.manual_analysis;

import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.model.UUIDEntity;
import com.leviatan.backend.model.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualMatchAnalysis extends UUIDEntity {

    @ManyToOne
    private User user;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private MatchInfo matchInfo;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private ManualMatchSideAnalysis redSideAnalysis;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private ManualMatchSideAnalysis blueSideAnalysis;


    public static ManualMatchAnalysis from(ManualMatchAnalysisDto matchAnalysis, User user) {
        return ManualMatchAnalysis.builder()
                .user(user)
                .matchInfo(matchAnalysis.getMatchInfo())
                .redSideAnalysis(matchAnalysis.getRedSideAnalysis())
                .blueSideAnalysis(matchAnalysis.getBlueSideAnalysis())
                .build();
    }
}
