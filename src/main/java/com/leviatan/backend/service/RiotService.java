package com.leviatan.backend.service;

import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.league.Region;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public EventResponse getWards(String matchId) throws Exception {
        try {
            User user  = sessionUtils.getLoggedUserInfo();
            Match match = matchRepository.findByIdAndOrganization_Id(matchId, user.getOrganization().getId()).orElseThrow(() -> new NotFoundException("Match not found"));
            return getEvents(match, List.of("WARD_PLACED", "WARD_KILL"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("League API Error");
        }
    }

    public EventResponse getContext(String matchId) throws Exception {
        try {
            User user  = sessionUtils.getLoggedUserInfo();
            Match match = matchRepository.findByIdAndOrganization_Id(matchId, user.getOrganization().getId()).orElseThrow(() -> new NotFoundException("Match not found"));
            return getEvents(match, List.of("ITEM_PURCHASED", "ITEM_SOLD", "ITEM_DESTROYED", "ITEM_UNDO", "CHAMPION_KILL", "LEVEL_UP"));
        } catch (Exception e) {
            throw new Exception("League API Error");
        }
    }

    public EventResponse getObjectives(String matchId) throws Exception {
        try {
            User user  = sessionUtils.getLoggedUserInfo();
            Match match = matchRepository.findByIdAndOrganization_Id(matchId, user.getOrganization().getId()).orElseThrow(() -> new NotFoundException("Match not found"));
            return getEvents(match, List.of("TURRET_PLATE_DESTROYED", "BUILDING_KILL", "ELITE_MONSTER_KILL", "DRAGON_SOUL_GIVEN"));
        } catch (Exception e) {
            throw new Exception("League API Error");
        }
    }

    private EventResponse getEvents(Match match, List<String> types) throws Exception {
        JSONObject timeline = riotMatchV5(match.getMatchId(), match.getRegion());
        Map<Integer, String> players = new HashMap<>();

        for (Object participant : timeline.getJSONObject("info").getJSONArray("participants")) {
            JSONObject participantJson = (JSONObject) participant;
            Integer participantId = ((JSONObject) participant).getInt("participantId");
            String player = getPlayerName(participantJson.getString("puuid"), match.getRegion());
            players.put(participantId, player);
        }

        List<JSONObject> events = new ArrayList<>();

        timeline.getJSONObject("info").getJSONArray("frames").forEach(frame -> {
            ((JSONObject) frame).getJSONArray("events").forEach(event -> events.add((JSONObject) event));
        });

        JSONObject first = events.get(0);
        events.remove(0);
        first.remove("type");

        return new EventResponse(
                first.toMap(),
                events.stream()
                        .filter(event -> types.contains(event.getString("type")))
                        .map(event -> {
                            Integer pid = null;
                            if (!event.isNull("participantId")) {
                                pid = event.getInt("participantId");
                                event.remove("participantId");
                            }
                            else if (!event.isNull("creatorId")) {
                                pid = event.getInt("creatorId");
                                event.remove("creatorId");
                            }
                            else if (!event.isNull("killerId")) {
                                pid = event.getInt("killerId");
                                event.remove("killerId");
                            }
                            if (pid != null) event.put("player", players.get(pid));
                            return event.toMap();
                        })
                        .collect(Collectors.toList())
        );
    }

    private String getPlayerName(String puuid, Region region) throws Exception {
        try {
            JSONObject player = riotSummonerV4ByPuuid(puuid, region);
            return player.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("League API Error");
        }
    }

    public String getPlayerBySummonerName(String summonerName, Region region){
        try {
            JSONObject player = riotSummonerV4BySummonerName(summonerName, region);
            return player.getString("puuid");
        } catch (Exception e) {
            return null;
        }
    }

    private JSONObject riotMatchV5(String matchId, Region region) throws URISyntaxException, IOException, InterruptedException {
        return riotRequest("/lol/match/v5/matches/" + matchId + "/timeline", mapRiotV5ApiRegion(region));
    }

    private JSONObject riotSummonerV4ByPuuid(String puuid, Region region) throws URISyntaxException, IOException, InterruptedException {
       return riotRequest("/lol/summoner/v4/summoners/by-puuid/" + puuid, mapRiotV4ApiRegion(region));
    }

    private JSONObject riotSummonerV4BySummonerName(String summonerName, Region region) throws URISyntaxException, IOException, InterruptedException {
        return riotRequest("/lol/summoner/v4/summoners/by-name/" + summonerName, mapRiotV4ApiRegion(region));
    }

    private JSONObject riotRequest(String url, String region) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(
                new URI("https://" + region + ".api.riotgames.com" + url + "?api_key=" + riotApiKey)
        ).GET().build();
        return new JSONObject(HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString()).body());
    }

    private String mapRiotV5ApiRegion(Region region) {
        String apiRegion;
        switch (region) {
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
        return apiRegion;
    }

    private String mapRiotV4ApiRegion(Region region) {
        String apiRegion;
        switch (region) {
            case EUNE:
                apiRegion = "eun1";
                break;
            case EUW:
                apiRegion = "euw1";
                break;
            case TR:
                apiRegion = "tr1";
                break;
            case RU:
                apiRegion = "ru";
                break;
            case NA:
                apiRegion = "na1";
                break;
            case BR:
                apiRegion = "br1";
                break;
            case LAN:
                apiRegion = "la1";
                break;
            case LAS:
                apiRegion = "la2";
                break;
            case OCE:
                apiRegion = "oc1";
                break;
            case KR:
                apiRegion = "kr";
                break;
            case JP:
                apiRegion = "jp1";
                break;
            default:
                return null;
        }
        return apiRegion;
    }
}
