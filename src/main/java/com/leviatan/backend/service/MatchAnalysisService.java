package com.leviatan.backend.service;

import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.factory.ManualMatchAnalysisResultFactory;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.repository.ManualMatchAnalysisRepository;
import com.leviatan.backend.repository.MatchAnalysisRepository;
import com.leviatan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchAnalysisService {

    private final MatchAnalysisRepository matchAnalysisRepository;
    private final ManualMatchAnalysisRepository manualMatchAnalysisRepository;
    private final UserRepository userRepository;
    private final ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory;

    @Autowired
    public MatchAnalysisService(MatchAnalysisRepository matchAnalysisRepository, ManualMatchAnalysisRepository manualMatchAnalysisRepository, UserRepository userRepository, ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory) {
        this.matchAnalysisRepository = matchAnalysisRepository;
        this.manualMatchAnalysisRepository = manualMatchAnalysisRepository;
        this.userRepository = userRepository;
        this.manualMatchAnalysisResultFactory = manualMatchAnalysisResultFactory;
    }

    public MatchAnalysis saveMatchAnalysis(MatchAnalysisDto matchAnalysis) {
        User user = getLoggedUser();
        return matchAnalysisRepository.save(MatchAnalysis.from(matchAnalysis, user));
    }

    public MatchAnalysis getMatchAnalysis(String analysisId) {
        return matchAnalysisRepository.findById(analysisId).orElseThrow(() -> new NotFoundException("Analysis not found"));
    }

    public List<MatchAnalysis> getAllMatchAnalyses() {
        return matchAnalysisRepository.findAll();
    }

    public ManualMatchAnalysis saveManualMatchAnalysis(ManualMatchAnalysisDto matchAnalysis) {
        User user = getLoggedUser();
        return manualMatchAnalysisRepository.save(ManualMatchAnalysis.from(matchAnalysis, user));
    }

    public List<ManualMatchAnalysis> getAllManualMatchAnalyses() {
        return manualMatchAnalysisRepository.findAll();
    }

    private User getLoggedUser(){
        return userRepository.findUserByEmail("test@gmail.com").orElseThrow(() -> new NotFoundException("User not found"));
    }
}
