package com.leviatan.backend.model;

import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Position;
import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Played extends UUIDEntity {

    private Team team;
    private Position position;
    private Champion champion;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

}
