package com.leviatan.backend.service;

import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.MatchRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RiotService {

    @Value("${leviatan.app.riotApiKey}")
    private String riotApiKey;

    private final MatchRepository matchRepository;
    private final SessionUtils sessionUtils;

    @Autowired
    public RiotService(MatchRepository matchRepository, SessionUtils sessionUtils) {
        this.matchRepository = matchRepository;
        this.sessionUtils = sessionUtils;
    }

    public String getWards(String matchId) {
        try {
            JSONObject timeline = riotMatchV5(matchId);
            if (timeline == null) return null;
            Map<Integer, String> participants = timeline
                .getJSONObject("info").getJSONArray("participants").toList().stream().map(p -> (JSONObject) p)
                .collect(Collectors.toMap(p -> p.getInt("participantId"), p -> p.getString("timeline")));

            System.out.println(participants.get(0));
            return timeline.getJSONObject("info").getJSONArray("frames").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public JSONObject getContext(String matchId) {
        try {
            return riotMatchV5(matchId);
        } catch (Exception e) {
            return null;
        }
    }

    public JSONObject getObjectives(String matchId) {
        try {
            return riotMatchV5(matchId);
        } catch (Exception e) {
            return null;
        }
    }

    private JSONObject riotMatchV5(String matchId) throws URISyntaxException, IOException, InterruptedException {
        User user  = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.findByIdAndUser_Id(matchId, user.getId()).orElse(null);
        if (match == null) return null;
        String apiRegion;
        switch (match.getRegion()) {
            case EUNE:
            case EUW:
            case TR:
            case RU:
                apiRegion = "europe";
                break;
            case NA:
            case BR:
            case LAN:
            case LAS:
                apiRegion = "americas";
                break;
            case OCE:
                apiRegion = "sea";
                break;
            case KR:
            case JP:
                apiRegion = "asia";
                break;
            default:
                return null;
        }

        HttpRequest request = HttpRequest.newBuilder().uri(
            new URI("https://" + apiRegion + ".api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "/timeline?api_key=" + riotApiKey)
        ).GET().build();

        return new JSONObject(HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString()).body());
    }
}
