package com.leviatan.backend.service;

import com.leviatan.backend.dto.MatchPaginationDto;
import com.leviatan.backend.dto.ReducedAnalysisDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisDto;
import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.manual_analysis.ManualMatchAnalysisResult;
import com.leviatan.backend.dto.manual_analysis.ManualMatchResultRequestDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.factory.ManualMatchAnalysisResultFactory;
import com.leviatan.backend.model.*;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.model.league.Position;
import com.leviatan.backend.model.league.Region;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import com.leviatan.backend.model.pagination.AnalysisType;
import com.leviatan.backend.repository.*;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private final MatchRepository matchRepository;

    private final RiotService riotService;

    private final PlayerService playerService;

    private final PlayedRepository playedRepository;

    @Autowired
    public MatchAnalysisService(MatchAnalysisRepository matchAnalysisRepository, ManualMatchAnalysisRepository manualMatchAnalysisRepository, ManualMatchAnalysisResultFactory manualMatchAnalysisResultFactory, SessionUtils sessionUtils, AnalysisRepository analysisRepository, MatchRepository matchRepository, RiotService riotService, PlayerService playerService, PlayedRepository playedRepository) {
        this.matchAnalysisRepository = matchAnalysisRepository;
        this.manualMatchAnalysisRepository = manualMatchAnalysisRepository;
        this.manualMatchAnalysisResultFactory = manualMatchAnalysisResultFactory;
        this.sessionUtils = sessionUtils;
        this.analysisRepository = analysisRepository;
        this.matchRepository = matchRepository;
        this.riotService = riotService;
        this.playerService = playerService;
        this.playedRepository = playedRepository;
    }

    public MatchAnalysis saveMatchAnalysis(MatchAnalysisDto matchAnalysis) {
        User user = sessionUtils.getLoggedUserInfo();
        Region region = matchAnalysis.getRegion() != null ? matchAnalysis.getRegion() : Region.LAS;

        Match match = Match.builder()
                .matchId(matchAnalysis.getMatchId())
                .matchDate(matchAnalysis.getMatchDate())
                .duration(matchAnalysis.getMatchDuration())
                .region(region)
                .organization(user.getOrganization())
                .build();
        matchRepository.save(match);

        // for each player find or create and create a new played entry
        List<Player> players = matchAnalysis.getPlayers().stream()
                .map(playerMeta -> {
                    Player player = playerService.findBySummonerName(playerMeta.getSummonerName());
                    if (player == null) {
                        player = Player.builder()
                                .playerId(riotService.getPlayerBySummonerName(playerMeta.getSummonerName(), region))
                                .summonerName(playerMeta.getSummonerName())
                                .organization(user.getOrganization())
                                .build();
                        player = playerService.save(player);
                    }

                    Played played = Played.builder()
                            .player(player)
                            .match(match)
                            .team(playerMeta.getTeam())
                            .champion(Champion.findByName(playerMeta.getChampionName()))
                            .position(Position.valueOf(playerMeta.getPosition()))
                            .cs(playerMeta.getScore().getCreepScore())
                            .score(
                                Score.builder()
                                    .kills(playerMeta.getScore().getKills())
                                    .deaths(playerMeta.getScore().getDeaths())
                                    .assists(playerMeta.getScore().getAssists())
                                    .build()
                            )
                            .build();
                    played = playedRepository.save(played);

                    return player;
                })
                .collect(Collectors.toList());

        // save analysis data in table

        return matchAnalysisRepository.save(MatchAnalysis.from(matchAnalysis, user.getOrganization()));
    }

    public Analysis getMatchAnalysis(String analysisId) {
        return analysisRepository.findById(analysisId).orElseThrow(() -> new NotFoundException("Analysis not found"));
    }

    public List<MatchAnalysisDto> getAllMatchAnalyses() {
        return matchAnalysisRepository.findAllByOrganization_Id(sessionUtils.getLoggedUserInfo().getOrganization().getId()).stream().map(MatchAnalysisDto::from).collect(Collectors.toList()).subList(0, 1);
    }

    public ManualMatchAnalysis saveManualMatchAnalysis(ManualMatchAnalysisDto matchAnalysis) {
        User user = sessionUtils.getLoggedUserInfo();
        return manualMatchAnalysisRepository.save(ManualMatchAnalysis.from(matchAnalysis, user.getOrganization()));
    }

    public ManualMatchAnalysisResult getManualAnalysisResult(ManualMatchResultRequestDto requestDto) {
        List<ManualMatchAnalysis> analyses = manualMatchAnalysisRepository.findAllById(requestDto.getMatchIds());
        return manualMatchAnalysisResultFactory.convert(analyses, requestDto.getTeams());
    }

    public List<ManualMatchAnalysis> getAllManualMatchAnalyses() {
        return manualMatchAnalysisRepository.findAllByOrganization_Id(sessionUtils.getLoggedUserInfo().getOrganization().getId());
    }

    public Page<ReducedAnalysisDto> getAllAnalysesPaginated(MatchPaginationDto params) {
        final User loggedUser = sessionUtils.getLoggedUserInfo();
        final List<String> orderList = ANALYSIS_SORT_PROPERTIES.get(params.getProperty());

        final PageRequest pageRequest = PageRequest.of(
                params.getPage(),
                params.getSize(),
                Sort.by(getOrderList(params.getDirection(), orderList)));

        if (params.getAnalysisType().equals(AnalysisType.ALL)){
            return analysisRepository.findAllAnalysesPaginated(loggedUser.getOrganization().getId(), pageRequest).map(Analysis::toReducedDto);
        } else {
            return analysisRepository.findAnalysesPaginated(
                    loggedUser.getOrganization().getId(),
                    params.getAnalysisType().equals(AnalysisType.MANUAL_ANALYSIS) ? "ManualMatchAnalysis" : "MatchAnalysis",
                    pageRequest).map(Analysis::toReducedDto);
        }
    }

    @Transactional
    public void deleteMatch(String analysisId) {
        final User loggedUser = sessionUtils.getLoggedUserInfo();
        analysisRepository.deleteAnalysisByIdAndOrganization_Id(analysisId, loggedUser.getOrganization().getId());
    }

    private List<Sort.Order> getOrderList(Sort.Direction direction, List<String> orderList) {
        List<Sort.Order> orders = new ArrayList<>();
        if (direction == ASC)
            orderList.forEach(up -> orders.add(new Sort.Order(ASC, up, Sort.NullHandling.NULLS_LAST)));
        else orderList.forEach(up -> orders.add(new Sort.Order(DESC, up, Sort.NullHandling.NULLS_LAST)));
        return orders;
    }
}
