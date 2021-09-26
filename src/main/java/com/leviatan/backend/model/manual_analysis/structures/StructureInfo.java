package com.leviatan.backend.model.manual_analysis.structures;

import lombok.Data;

import java.io.Serializable;

@Data
public class StructureInfo implements Serializable {
    private String structureId;
    private Double timestamp;
    private String summonerName;
}
