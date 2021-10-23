package com.leviatan.backend.dto;

import com.leviatan.backend.model.analysis.position.PlayerReducedMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerFrameMetadata implements Serializable {

    private List<PlayerReducedMetadata> metadata;
    private Double timestamp;
}
