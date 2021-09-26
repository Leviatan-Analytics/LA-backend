package com.leviatan.backend.controller;

import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.service.MatchAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins = "*")
public class MatchAnalysisController {

    private final MatchAnalysisService matchAnalysisService;

    @Autowired
    public MatchAnalysisController(MatchAnalysisService matchAnalysisService){
        this.matchAnalysisService = matchAnalysisService;
    }

    @PostMapping
    public MatchAnalysis saveMatchAnalysis(@RequestBody @Valid MatchAnalysisDto matchAnalysis){
        return matchAnalysisService.saveMatchAnalysis(matchAnalysis);
    }

    @GetMapping
    public List<MatchAnalysis> getAllMatchAnalyses() {
        return matchAnalysisService.getAllMatchAnalyses();
    }

    @GetMapping("/{analysisId}")
    public MatchAnalysis getMatchAnalysis(@PathVariable(name = "analysisId") String analysisId){
        return matchAnalysisService.getMatchAnalysis(analysisId);
    }

    @PostMapping("/manual")
    public ManualMatchAnalysis saveManualMatchAnalysis(@RequestBody @Valid ManualMatchAnalysisDto matchAnalysis){
        return matchAnalysisService.saveManualMatchAnalysis(matchAnalysis);
    }

    @GetMapping("/manual")
    public List<ManualMatchAnalysis> getAllManualMatchAnalysis() {
        return matchAnalysisService.getAllManualMatchAnalyses();
    }
}
