package com.leviatan.backend.controller;

import com.leviatan.backend.dto.MatchPaginationDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisResult;
import com.leviatan.backend.dto.manual_analysis.ManualMatchResultRequestDto;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.analysis.metadata.PlayerMetadata;
import com.leviatan.backend.model.analysis.metadata.TrackInfo;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.model.pagination.AnalysisType;
import com.leviatan.backend.model.pagination.AnalysisSort;
import com.leviatan.backend.service.MatchAnalysisService;
import com.leviatan.backend.service.ReducedAnalysisDto;
import com.leviatan.backend.utils.TCXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.jakubtrzcinski.tcxparser.TcxParser;

import javax.validation.Valid;
import java.io.File;
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
    public Page<ReducedAnalysisDto> getAll(
            @RequestParam(value = "size", required = false) Optional<Integer> size,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "direction", required = false) Optional<Sort.Direction> direction,
            @RequestParam(value = "property", required = false) Optional<AnalysisSort> property,
            @RequestParam(value = "analysisType", required = false) Optional<AnalysisType> analysisInclude
    ) {
        MatchPaginationDto params = MatchPaginationDto.builder()
                .size(size.orElse(1000000000))
                .page(page.orElse(0))
                .direction(direction.orElse(Sort.Direction.DESC))
                .property(property.orElse(AnalysisSort.ANALYSIS_DATE))
                .analysisType(analysisInclude.orElse(AnalysisType.ALL))
                .build();
        return matchAnalysisService.getAllAnalysesPaginated(params);
    }

    @PutMapping("/trackdata/{id}")
    public MatchAnalysis addTcxToPlayer(String champ, File garminFile, @PathVariable String id) throws Exception {
        TCXReader reader = new TCXReader();
        reader.parse(garminFile);
        TrackInfo track = new TrackInfo(reader.getBPMMean(), reader.getBPMTracklist(), reader.getTimeTracklist());

        MatchAnalysis matchAnalysis = matchAnalysisService.getMatchAnalysisById(id);
        List<PlayerMetadata> players = matchAnalysis.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getChampionName().equals(champ)) players.get(i).setTrack(track);
        }
        matchAnalysis.setPlayers(players);
        return matchAnalysisService.replaceMatchAnalysis(matchAnalysis);
    }

    @DeleteMapping("/{analysisId}")
    public void deleteAnalysis(@PathVariable(name = "analysisId") String analysisId) {
        matchAnalysisService.deleteMatch(analysisId);
    }
}
