package com.leviatan.backend.dto.manual_analysis.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicksCount {

    private String championId;
    private Integer count;
}
