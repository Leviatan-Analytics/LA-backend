package com.leviatan.backend.model.analysis.metadata;

import com.leviatan.backend.model.league.Team;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerMetadata implements Serializable {
    private String championName;
    private Team team;
    private List<ItemInfo> items;
    private Integer level;
    private String position;
    private RuneInfo runes;
    private ScoreInfo score;
    private String summonerName;
    private SpellInfo spells;
}
