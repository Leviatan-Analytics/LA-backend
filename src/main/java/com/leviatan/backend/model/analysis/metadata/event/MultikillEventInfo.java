package com.leviatan.backend.model.analysis.metadata.event;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultikillEventInfo extends EventInfo implements Serializable {
    private Integer killStreak;
    private String killerName;
}
