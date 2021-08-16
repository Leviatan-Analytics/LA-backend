package com.leviatan.backend.model.analysis.wards;

import com.leviatan.backend.model.analysis.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchWardInfo implements Serializable {
    private Team team;
    private WardType wardType;
    private Double x;
    private Double y;
}
