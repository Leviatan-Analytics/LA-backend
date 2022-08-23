package com.leviatan.backend.model.analysis.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackInfo {
    private short bpmMean;
    private List<Short> bpmList;
    private List<Date> datePerSecond;
}
