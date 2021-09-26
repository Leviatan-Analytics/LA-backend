package com.leviatan.backend.dto.manual_analysis.jungle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvadeInfo {

    private List<String> summonerNames;
    private String bushId;
}
