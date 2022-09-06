package com.leviatan.backend.controller;

import com.leviatan.backend.model.Flag;
import com.leviatan.backend.model.Note;
import com.leviatan.backend.service.FlagService;
import com.leviatan.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "*")
public class MatchController {

    private final FlagService flagService;

    private final NoteService noteService;

    @Autowired
    public MatchController(FlagService flagService, NoteService noteService) {
        this.flagService = flagService;
        this.noteService = noteService;
    }

    @PostMapping("/{matchId}/flag")
    public Flag flagMatch(@PathVariable String matchId) {
        return flagService.createFlag(matchId);
    }

    @DeleteMapping("/{matchId}/flag")
    public void unflagMatch(@PathVariable String matchId) {
        flagService.deleteFlag(matchId);
    }

    @PostMapping("/{matchId}/note")
    public Note addNoteToMatch(@PathVariable String matchId, @RequestBody String note) {
        return noteService.addNoteToMatch(matchId, note);
    }

    @PutMapping("/{matchId}/note/{noteId}")
    public Note updateNoteToMatch(@PathVariable String matchId, @PathVariable String noteId, @RequestBody String note) {
        return noteService.updateNoteToMatch(matchId, noteId, note);
    }

    @DeleteMapping("/{matchId}/note")
    public void deleteNoteFromMatch(@PathVariable String matchId) {
        noteService.deleteNoteFromMatch(matchId);
    }
}
