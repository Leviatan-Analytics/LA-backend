package com.leviatan.backend.controller;

import com.leviatan.backend.service.EventResponse;
import com.leviatan.backend.service.RiotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riot")
@CrossOrigin(origins = "*")
public class RiotController {

    private final RiotService riotService;

    @Autowired
    public RiotController(RiotService riotService) {
        this.riotService = riotService;
    }


    @GetMapping("/{matchId}/wards")
    public EventResponse getWards(@PathVariable String matchId) throws Exception {
        return riotService.getWards(matchId);
    }

    @GetMapping("/{matchId}/wards")
    public EventResponse getContext(@PathVariable String matchId) throws Exception {
        return riotService.getContext(matchId);
    }

    @GetMapping("/{matchId}/wards")
    public EventResponse getObjectives(@PathVariable String matchId) throws Exception {
        return riotService.getObjectives(matchId);
    }
}
