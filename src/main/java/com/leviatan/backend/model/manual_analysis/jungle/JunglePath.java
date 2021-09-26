package com.leviatan.backend.model.manual_analysis.jungle;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JunglePath implements Serializable {

    private String summonerName;
    private Boolean leash;
    private List<JunglePathPosition> path;
}
