package com.leviatan.backend.dto;

import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDto {

    private String topic;

    private String content;

    private List<Champion> relatedChampions;

    private Team side;

    private String team;

    private String summoner;

    private Integer timestamp;

    private Boolean includesWards;

    private Integer x;

    private Integer y;
}
