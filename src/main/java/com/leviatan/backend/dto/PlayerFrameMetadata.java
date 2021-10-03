package com.leviatan.backend.dto;

import com.leviatan.backend.model.analysis.position.PlayerReducedMetadata;

import java.io.Serializable;
import java.util.List;

public class PlayerFrameMetadata implements Serializable {

    private List<PlayerReducedMetadata> metadata;
    private Double timestamp;
}
