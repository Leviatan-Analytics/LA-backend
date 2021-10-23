package com.leviatan.backend.model.analysis.position;

import com.leviatan.backend.model.analysis.metadata.ItemInfo;
import com.leviatan.backend.model.analysis.metadata.ScoreInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerReducedMetadata implements Serializable {

    private String championName;
    private List<ItemInfo> items;
    private Integer level;
    private ScoreInfo score;
    private String summonerName;
}
