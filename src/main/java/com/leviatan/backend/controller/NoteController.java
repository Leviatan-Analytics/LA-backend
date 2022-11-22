package com.leviatan.backend.controller;

import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.model.Note;
import com.leviatan.backend.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "*")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public List<NoteDto> getNotes(@RequestParam Optional<Integer> page,
                                  @RequestParam Optional<String> team,
                                  @RequestParam Optional<String> topic,
                                  @RequestParam Optional<String> search,
                                  @RequestParam Optional<List<String>> summoner,
                                  @RequestParam Optional<Boolean> flag) {
        return noteService.getNotes(page.orElse(0), team, topic, search, flag, summoner);
    }

    @GetMapping("/topic")
    public List<String> getNoteTopics() {
        return noteService.getNoteTopics();
    }

    @GetMapping("/team")
    public List<String> getNoteTeams() {
        return noteService.getNoteTeams();
    }

    @GetMapping("/summoner")
    public List<String> getNoteSummoner() { return noteService.getNoteSummoners(); }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable("noteId") String noteId) {
        noteService.deleteNote(noteId);
    }

    @PutMapping("/{noteId}/flag")
    public Note flagNote(@PathVariable("noteId") String noteId) { return noteService.flagNote(noteId, true); }

    @DeleteMapping("/{noteId}/flag")
    public Note unFlagNote(@PathVariable("noteId") String noteId) { return noteService.flagNote(noteId, false); }
}
