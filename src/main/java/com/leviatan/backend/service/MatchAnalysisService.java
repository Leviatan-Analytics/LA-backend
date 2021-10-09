package com.leviatan.backend.service;

import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisResult;
import com.leviatan.backend.dto.manual_analysis.ManualMatchResultRequestDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.factory.ManualMatchAnalysisResultFactory;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.repository.ManualMatchAnalysisRepository;
import com.leviatan.backend.repository.MatchAnalysisRepository;
import com.leviatan.backend.repository.UserRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchAnalysisService {

    private final MatchAnalysisRepository matchAnalysisRepository;
    private final ManualMatchAnalysisRepository manualMatchAnalysisRepository;
    private final ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory;
    private final SessionUtils sessionUtils;

    @Autowired
    public MatchAnalysisService(MatchAnalysisRepository matchAnalysisRepository, ManualMatchAnalysisRepository manualMatchAnalysisRepository, ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory, SessionUtils sessionUtils) {
        this.matchAnalysisRepository = matchAnalysisRepository;
        this.manualMatchAnalysisRepository = manualMatchAnalysisRepository;
        this.manualMatchAnalysisResultFactory = manualMatchAnalysisResultFactory;
        this.sessionUtils = sessionUtils;
    }

    public MatchAnalysis saveMatchAnalysis(MatchAnalysisDto matchAnalysis) {
        User user = sessionUtils.getLoggedUserInfo();
        return matchAnalysisRepository.save(MatchAnalysis.from(matchAnalysis, user));
    }

    public MatchAnalysis getMatchAnalysis(String analysisId) {
        return matchAnalysisRepository.findById(analysisId).orElseThrow(() -> new NotFoundException("Analysis not found"));
    }

    public List<MatchAnalysis> getAllMatchAnalyses() {
        return matchAnalysisRepository.findAllByUser_Id(sessionUtils.getLoggedUserInfo().getId());
    }

    public ManualMatchAnalysis saveManualMatchAnalysis(ManualMatchAnalysisDto matchAnalysis) {
        User user = sessionUtils.getLoggedUserInfo();
        return manualMatchAnalysisRepository.save(ManualMatchAnalysis.from(matchAnalysis, user));
    }

    public ManualMatchAnalysisResult getManualAnalysisResult(ManualMatchResultRequestDto requestDto) {
        List<ManualMatchAnalysis> analyses = manualMatchAnalysisRepository.findAllById(requestDto.getMatchIds());
        return manualMatchAnalysisResultFactory.convert(analyses, requestDto.getTeams());
    }

    public List<ManualMatchAnalysis> getAllManualMatchAnalyses() {
        return manualMatchAnalysisRepository.findAllByUser_Id(sessionUtils.getLoggedUserInfo().getId());
    }

}
