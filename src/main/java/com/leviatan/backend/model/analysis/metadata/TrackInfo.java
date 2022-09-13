package com.leviatan.backend.model.analysis.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackInfo {
    private short averageBPM;
    private List<Short> bpmPerSecond;
    private List<Date> timestampsPerSecond;
}
