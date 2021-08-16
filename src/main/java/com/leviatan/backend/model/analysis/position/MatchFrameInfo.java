package com.leviatan.backend.model.analysis.position;

import com.leviatan.backend.model.analysis.wards.MatchWardInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchFrameInfo implements Serializable {
    private Long timeStamp;
    private List<PlayerPosition> players;
    private List<MatchWardInfo> wards;
}
