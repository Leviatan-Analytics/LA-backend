package com.leviatan.backend.model.analysis.metadata.event;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AceEvent extends EventInfo implements Serializable {
    private String acer;
    private String acingTeam;
}
