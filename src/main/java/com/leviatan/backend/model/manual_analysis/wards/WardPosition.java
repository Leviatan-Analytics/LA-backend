package com.leviatan.backend.model.manual_analysis.wards;

import com.leviatan.backend.model.analysis.wards.WardType;
import lombok.Data;

import java.io.Serializable;

@Data
public class WardPosition implements Serializable {
    private Integer x;
    private Integer y;
    private Double timestamp;
    private WardType wardType;
    private String summonerName;
}
