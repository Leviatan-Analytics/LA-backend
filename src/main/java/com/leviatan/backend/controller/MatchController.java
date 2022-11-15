package com.leviatan.backend.controller;

import com.leviatan.backend.dto.MatchAnalysisDto;
import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.model.Analysis;
import com.leviatan.backend.model.Flag;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.Played;
import com.leviatan.backend.model.analysis.MatchAnalysis;
import com.leviatan.backend.model.analysis.metadata.TrackInfo;
import com.leviatan.backend.repository.PlayedRepository;
import com.leviatan.backend.service.FlagService;
import com.leviatan.backend.service.MatchAnalysisService;
import com.leviatan.backend.service.NoteService;
import com.leviatan.backend.utils.TCXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public NoteDto addNoteToMatch(@PathVariable String matchId, @RequestBody NoteDto note) {
        return noteService.addNoteToMatch(matchId, note);
    }

    @PutMapping("/{matchId}/note/{noteId}")
    public NoteDto updateNoteToMatch(@PathVariable("noteId") String noteId, @RequestBody NoteDto note) {
        return noteService.updateNoteToMatch(noteId, note);
    }

    @PutMapping("/{matchId}/trackdata/{playerId}")
    public Played addTrackDataToMatch(@PathVariable("matchId") String matchId, @PathVariable("playerId") String playedId, @RequestParam("file") MultipartFile garminFile) throws Exception {
        TCXReader reader = new TCXReader();
        reader.parse(garminFile);
        TrackInfo track = new TrackInfo(reader.getBPMMean(), reader.getTracklist());

        Played played = playedRepository.getByPlayer_IdAndMatch_Id(playedId, matchId);
        played.setTrackInfo(track);
        return playedRepository.save(played);
    }


}
