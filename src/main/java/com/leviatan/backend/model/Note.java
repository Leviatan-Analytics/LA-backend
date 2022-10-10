package com.leviatan.backend.model;

import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note extends UUIDEntity{

    @ManyToOne
    private Match match;

    @ManyToOne
    private User author;

    private String topic;

    private String content;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private List<Champion> relatedChampions;

    private Team side;

    @ManyToOne
    private Organization organization;

    private String team;

    @ManyToOne
    private Player player;

    private Integer timestamp;

    private Boolean includesWards;

    private Integer x;

    private Integer y;

    public NoteDto toDto() {
        return NoteDto.builder()
                .topic(topic)
                .content(content)
                .relatedChampions(relatedChampions)
                .side(side)
                .team(team)
                .summoner(player.getSummonerName())
                .timestamp(timestamp)
                .includesWards(includesWards)
                .x(x)
                .y(y)
                .build();
    }
}
