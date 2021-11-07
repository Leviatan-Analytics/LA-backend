package com.leviatan.backend.controller;

import com.leviatan.backend.dto.MatchPaginationDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisResult;
import com.leviatan.backend.dto.manual_analysis.ManualMatchResultRequestDto;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.model.pagination.AnalysisType;
import com.leviatan.backend.model.pagination.AnalysisSort;
import com.leviatan.backend.service.MatchAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public Analysis getMatchAnalysis(@PathVariable(name = "analysisId") String analysisId){
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

    @GetMapping("/manual/multiple")
    public ManualMatchAnalysisResult getManualMatchesResult(@RequestBody ManualMatchResultRequestDto manualMatchResultRequestDto) {
        return matchAnalysisService.getManualAnalysisResult(manualMatchResultRequestDto);
    }

    @GetMapping("/all")
    public Page<Analysis> getAll(
            @RequestParam(value = "size", required = false) Optional<Integer> size,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "direction", required = false) Optional<Sort.Direction> direction,
            @RequestParam(value = "property", required = false) Optional<AnalysisSort> property,
            @RequestParam(value = "analysisType", required = false) Optional<AnalysisType> analysisInclude
    ) {
        MatchPaginationDto params = MatchPaginationDto.builder()
                .size(size.orElse(20))
                .page(page.orElse(0))
                .direction(direction.orElse(Sort.Direction.DESC))
                .property(property.orElse(AnalysisSort.ID))
                .analysisType(analysisInclude.orElse(AnalysisType.ALL))
                .build();
        return matchAnalysisService.getAllAnalysesPaginated(params);
    }

    @DeleteMapping("/{analysisId}")
    public void deleteAnalysis(@PathVariable(name = "analysisId") String analysisId) {
        matchAnalysisService.deleteMatch(analysisId);
    }
}
