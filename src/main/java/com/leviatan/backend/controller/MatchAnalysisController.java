package com.leviatan.backend.controller;

import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.service.MatchAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/analysis")
public class MatchAnalysisController {

    private final MatchAnalysisService matchAnalysisService;

    @Autowired
    public MatchAnalysisController(MatchAnalysisService matchAnalysisService){
        this.matchAnalysisService = matchAnalysisService;
    }

    @PostMapping
    public void saveMatchAnalysis(@RequestBody @Valid MatchAnalysisDto matchAnalysis){
        matchAnalysisService.saveMatchAnalysis(matchAnalysis);
    }

    @GetMapping
    public List<MatchAnalysisDto> getAllMatchAnalyses() {
        return matchAnalysisService.getAllMatchAnalyses();
    }

    @GetMapping("/{analysisId}")
    public MatchAnalysisDto getMatchAnalysis(@PathVariable(name = "analysisId") String analysisId){
        return matchAnalysisService.getMatchAnalysis(analysisId);
    }
}
