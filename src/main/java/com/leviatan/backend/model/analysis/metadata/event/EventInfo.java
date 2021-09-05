package com.leviatan.backend.model.analysis.metadata.event;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class EventInfo implements Serializable {
    private String eventId;
    private String eventName;
    private Double eventTime;
}
