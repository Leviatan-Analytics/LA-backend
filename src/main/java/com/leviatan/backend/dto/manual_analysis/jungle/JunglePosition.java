package com.leviatan.backend.dto.manual_analysis.jungle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JunglePosition {
    private String positionId;
    private List<String> nextPositions;
}
