package com.leviatan.backend.controller;

import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.service.NoteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "*")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public List<NoteDto> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/topic")
    public List<String> getNoteTopics() {
        return noteService.getNoteTopics();
    }

    @GetMapping("/team")
    public List<String> getNoteTeams() {
        return noteService.getNoteTeams();
    }
}
