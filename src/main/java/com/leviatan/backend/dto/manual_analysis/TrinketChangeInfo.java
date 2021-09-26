package com.leviatan.backend.dto.manual_analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrinketChangeInfo {
    private String summonerName;
    private Double percentage;
}
