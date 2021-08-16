package com.leviatan.backend.service;

import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.repository.MatchAnalysisRepository;
import com.leviatan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchAnalysisService {

    private final MatchAnalysisRepository matchAnalysisRepository;
    private final UserRepository userRepository;

    @Autowired
    public MatchAnalysisService(MatchAnalysisRepository matchAnalysisRepository, UserRepository userRepository) {
        this.matchAnalysisRepository = matchAnalysisRepository;
        this.userRepository = userRepository;
    }

    public void saveMatchAnalysis(MatchAnalysisDto matchAnalysis) {
        //Get logged user data
        User user = User.builder()
                .username("test")
                .email("test@gmail.com")
                .password("test123")
                .build();
        user = userRepository.save(user);
        matchAnalysisRepository.save(MatchAnalysis.from(matchAnalysis, user));
    }

    public MatchAnalysisDto getMatchAnalysis(String analysisId) {
        return MatchAnalysisDto.from(matchAnalysisRepository.findById(analysisId).orElseThrow(() -> new NotFoundException("Analysis not found")));
    }
}
