package com.leviatan.backend.dto.manual_analysis;

import com.leviatan.backend.model.league.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManualMatchResultRequestDto {

    private List<String> matchIds;
    private List<Team> teams;
}
