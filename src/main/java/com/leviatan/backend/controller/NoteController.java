package com.leviatan.backend.controller;

import com.leviatan.backend.dto.NoteDto;
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
                                  @RequestParam Optional<String> summoner) {
        return noteService.getNotes(page.orElse(0), team, topic);
    }

    @GetMapping("/topic")
    public List<String> getNoteTopics() {
        return noteService.getNoteTopics();
    }

    @GetMapping("/team")
    public List<String> getNoteTeams() {
        return noteService.getNoteTeams();
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable("noteId") String noteId) {
        noteService.deleteNote(noteId);
    }
}
