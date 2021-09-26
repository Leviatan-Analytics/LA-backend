package com.leviatan.backend.model.manual_analysis.jungle;

import lombok.Data;

import java.io.Serializable;

@Data
public class JunglePathPosition implements Serializable {
    private String positionId;
    private Double timestamp;
}
