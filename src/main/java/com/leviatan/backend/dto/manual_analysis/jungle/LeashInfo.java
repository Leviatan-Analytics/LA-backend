package com.leviatan.backend.dto.manual_analysis.jungle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeashInfo {
    private Double percentage;
    private String lane;
}
