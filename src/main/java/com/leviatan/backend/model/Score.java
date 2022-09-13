package com.leviatan.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {
    private int kills;
    private int deaths;
    private int assists;
}
