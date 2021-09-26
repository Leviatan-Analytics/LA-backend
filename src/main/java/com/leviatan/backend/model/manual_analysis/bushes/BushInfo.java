package com.leviatan.backend.model.manual_analysis.bushes;

import lombok.Data;

import java.io.Serializable;

@Data
public class BushInfo implements Serializable {
    private String bushId;
    private String summonerName;
    private Boolean isInvade;
}
