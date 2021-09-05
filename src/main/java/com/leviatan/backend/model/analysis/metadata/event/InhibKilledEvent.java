package com.leviatan.backend.model.analysis.metadata.event;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InhibKilledEvent extends EventInfo implements Serializable {
    private String inhibKilled;
    private String killerName;
}
