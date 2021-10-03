package com.leviatan.backend.model.analysis.position;

import com.leviatan.backend.model.analysis.metadata.ItemInfo;
import com.leviatan.backend.model.analysis.metadata.ScoreInfo;

import java.io.Serializable;
import java.util.List;

public class PlayerReducedMetadata implements Serializable {

    private String championName;
    private List<ItemInfo> items;
    private Integer level;
    private ScoreInfo score;
    private String summonerName;
}
