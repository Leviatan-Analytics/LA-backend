package com.leviatan.backend.controller;

import com.leviatan.backend.service.RiotService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/riot")
@CrossOrigin(origins = "*")
public class RiotController {

    private RiotService riotService;

    @Autowired
    public RiotController(RiotService riotService) {
        this.riotService = riotService;
    }


    @GetMapping("/{matchId}/wards")
    public String getWards(@PathVariable String matchId) throws URISyntaxException, IOException, InterruptedException {
        return riotService.getWards(matchId);
    }
}
