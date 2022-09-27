package com.leviatan.backend.model;

import com.leviatan.backend.model.analysis.metadata.TrackInfo;
import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Position;
import com.leviatan.backend.model.league.Team;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Played extends UUIDEntity {

    private Team team;
    private Position position;
    private Champion champion;
    private Integer gold;
    private Integer cs;
    private Double vision;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private TrackInfo trackInfo;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private Score score;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

}
