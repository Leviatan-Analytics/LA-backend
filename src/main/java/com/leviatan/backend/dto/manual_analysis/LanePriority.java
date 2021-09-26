package com.leviatan.backend.dto.manual_analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanePriority {

    private Double top;
    private Double mid;
    private Double bot;
}
