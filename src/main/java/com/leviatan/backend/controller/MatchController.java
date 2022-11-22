package com.leviatan.backend.controller;

import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.Flag;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.analysis.metadata.TrackFrame;
import com.leviatan.backend.model.analysis.metadata.TrackInfo;
import com.leviatan.backend.repository.PlayedRepository;
import com.leviatan.backend.service.FlagService;
import com.leviatan.backend.service.MatchAnalysisService;
import com.leviatan.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.leviatan.fit.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "*")
public class MatchController {

    private final FlagService flagService;

    private final NoteService noteService;

    private final MatchAnalysisService matchAnalysisService;

    private final PlayedRepository playedRepository;

    @Autowired
    public MatchController(FlagService flagService, NoteService noteService, MatchAnalysisService matchAnalysisService, PlayedRepository playedRepository) {
        this.flagService = flagService;
        this.noteService = noteService;
        this.playedRepository = playedRepository;
        this.matchAnalysisService = matchAnalysisService;
    }

    @GetMapping("/flag")
    public List<Match> getAllFlagged() {
        return flagService.getAllFlagged();
    }

    @PostMapping("/{matchId}/flag")
    public Flag flagMatch(@PathVariable String matchId) {
        return flagService.createFlag(matchId);
    }

    @DeleteMapping("/{matchId}/flag")
    public void unflagMatch(@PathVariable String matchId) {
        flagService.deleteFlag(matchId);
    }

    @GetMapping ("/{matchId}/note")
    public List<NoteDto> getAllNotes(@PathVariable String matchId) {
        return noteService.getNotesFromMatch(matchId);
    }

    @GetMapping ("/{matchId}/analysis")
    public Analysis getAnalysisFromMatch(@PathVariable String matchId) { return matchAnalysisService.getMatchAnalysisByMatchId(matchId); }

    @PostMapping("/{matchId}/note")
    public NoteDto addNoteToMatch(@PathVariable String matchId, @RequestBody @Valid NoteDto note) {
        return noteService.addNoteToMatch(matchId, note);
    }

    @PutMapping("/{matchId}/note/{noteId}")
    public NoteDto updateNoteToMatch(@PathVariable("noteId") String noteId, @RequestBody @Valid NoteDto note) {
        return noteService.updateNoteToMatch(noteId, note);
    }

    @PutMapping(value = "/{matchId}/trackdata/{playerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Played addTrackDataToMatch(@PathVariable("matchId") String matchId, @PathVariable("playerId") String playerId, @RequestParam("file") MultipartFile garminFile) throws Exception {
        FitDecoder decoder = new FitDecoder();
        FitMessages messages = decoder.decode(garminFile.getInputStream());

        List<TrackFrame> frames = messages.getRecordMesgs().stream()
                .map(record -> new ArrayList<>(record.getFields()))
                .map(r -> new TrackFrame(r.get(0).getIntegerValue(), r.get(3).getIntegerValue(), r.get(2).getIntegerValue()/100))
                .collect(Collectors.toList());

        double mean = frames.stream().mapToInt(TrackFrame::getBpm).average().orElse(0);

        TrackInfo track = new TrackInfo(mean, frames);

        Played played = playedRepository.getByPlayer_IdAndMatch_Id(playerId, matchId).orElseThrow(() -> new Exception("Player not found in match"));
        played.setTrackInfo(track);
        return playedRepository.save(played);
    }


}
