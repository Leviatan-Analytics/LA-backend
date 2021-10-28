package com.leviatan.backend.dto.manual_analysis;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchInfoDto {

    private String matchId;
    private LocalDateTime matchDate;
    private Long matchDuration;
}
