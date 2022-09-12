package com.leviatan.backend.service;

import com.leviatan.backend.model.Flag;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.FlagRepository;
import com.leviatan.backend.repository.MatchRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlagService {

    private final FlagRepository flagRepository;

    private final SessionUtils sessionUtils;

    private final MatchRepository matchRepository;

    @Autowired
    public FlagService(FlagRepository flagRepository, SessionUtils sessionUtils, MatchRepository matchRepository) {
        this.flagRepository = flagRepository;
        this.sessionUtils = sessionUtils;
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllFlagged() {
        User user = sessionUtils.getLoggedUserInfo();
        return flagRepository.findAllByUser(user).stream().map(Flag::getMatch).collect(Collectors.toList());
    }

    public Flag createFlag(String matchId) {
        User user = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.getById(matchId);

        Flag flag = new Flag();
        flag.setUser(user);
        flag.setMatch(match);

        return flagRepository.save(flag);
    }

    public void deleteFlag(String matchId) {
        User user = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.getById(matchId);

        Optional<Flag> flag = flagRepository.findByUserAndMatch_Id(user, match.getId());
        flag.ifPresent(flagRepository::delete);
    }
}
