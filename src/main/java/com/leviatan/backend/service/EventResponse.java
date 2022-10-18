package com.leviatan.backend.service;

import java.util.Map;
import java.util.List;

public class EventResponse {
    Map<String, Object> timestamp;
    List<Map<String, Object>> events;

    public EventResponse(Map<String, Object> timestamp, List<Map<String, Object>> events) {
        this.timestamp = timestamp;
        this.events = events;
    }

    public Map<String, Object> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Map<String, Object> timestamp) {
        this.timestamp = timestamp;
    }

    public List<Map<String, Object>> getEvents() {
        return events;
    }

    public void setEvents(List<Map<String, Object>> events) {
        this.events = events;
    }
}
