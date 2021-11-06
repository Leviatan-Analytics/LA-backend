package com.leviatan.backend.service;

import com.leviatan.backend.dto.MatchPaginationDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisResult;
import com.leviatan.backend.dto.manual_analysis.ManualMatchResultRequestDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.factory.ManualMatchAnalysisResultFactory;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.model.pagination.AnalysisType;
import com.leviatan.backend.repository.AnalysisRepository;
import com.leviatan.backend.repository.ManualMatchAnalysisRepository;
import com.leviatan.backend.repository.MatchAnalysisRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.leviatan.backend.utils.AnalysisUtils.ANALYSIS_SORT_PROPERTIES;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class MatchAnalysisService {

    private final MatchAnalysisRepository matchAnalysisRepository;
    private final ManualMatchAnalysisRepository manualMatchAnalysisRepository;
    private final ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory;
    private final SessionUtils sessionUtils;
    private final AnalysisRepository analysisRepository;

    @Autowired
    public MatchAnalysisService(MatchAnalysisRepository matchAnalysisRepository, ManualMatchAnalysisRepository manualMatchAnalysisRepository, ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory, SessionUtils sessionUtils, AnalysisRepository analysisRepository) {
        this.matchAnalysisRepository = matchAnalysisRepository;
        this.manualMatchAnalysisRepository = manualMatchAnalysisRepository;
        this.manualMatchAnalysisResultFactory = manualMatchAnalysisResultFactory;
        this.sessionUtils = sessionUtils;
        this.analysisRepository = analysisRepository;
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

    public Page<Analysis> getAllAnalysesPaginated(MatchPaginationDto params) {
        final User loggedUser = sessionUtils.getLoggedUserInfo();
        final List<String> orderList = ANALYSIS_SORT_PROPERTIES.get(params.getProperty());

        final PageRequest pageRequest = PageRequest.of(
                params.getPage(),
                params.getSize(),
                Sort.by(getOrderList(params.getDirection(), orderList)));

        if (params.getAnalysisType().equals(AnalysisType.ALL)){
            return analysisRepository.findAllAnalysesPaginated(loggedUser.getId(), pageRequest);
        } else {
            return analysisRepository.findAnalysesPaginated(
                    loggedUser.getId(),
                    params.getAnalysisType().equals(AnalysisType.MANUAL_ANALYSIS) ? "ManualMatchAnalysis" : "MatchAnalysis",
                    pageRequest);
        }
    }

    @Transactional
    public void deleteMatch(String analysisId) {
        final User loggedUser = sessionUtils.getLoggedUserInfo();
        analysisRepository.deleteAnalysisByIdAndUser_Id(analysisId, loggedUser.getId());
    }

    private List<Sort.Order> getOrderList(Sort.Direction direction, List<String> orderList) {
        List<Sort.Order> orders = new ArrayList<>();
        if (direction == ASC)
            orderList.forEach(up -> orders.add(new Sort.Order(ASC, up, Sort.NullHandling.NULLS_LAST)));
        else orderList.forEach(up -> orders.add(new Sort.Order(DESC, up, Sort.NullHandling.NULLS_LAST)));
        return orders;
    }
}
