package com.leviatan.backend.model;

import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Position;
import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Played extends UUIDEntity {

    private Team team;
    private Position position;
    private Champion champion;
    private Integer gold;
    private Integer cs;
    private Integer vision;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private Score score;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

}
