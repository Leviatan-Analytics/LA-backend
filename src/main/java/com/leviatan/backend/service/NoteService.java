package com.leviatan.backend.service;

import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.Note;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.MatchRepository;
import com.leviatan.backend.repository.NoteRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final SessionUtils sessionUtils;

    private final MatchRepository matchRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, SessionUtils sessionUtils, MatchRepository matchRepository) {
        this.noteRepository = noteRepository;
        this.sessionUtils = sessionUtils;
        this.matchRepository = matchRepository;
    }

    public Note addNoteToMatch(String matchId, String text) {
        User user = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.getById(matchId);

        Note note = new Note();
        note.setUser(user);
        note.setMatch(match);
        note.setText(text);

        return noteRepository.save(note);
    }

    public void deleteNoteFromMatch(String matchId) {
        User user = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.getById(matchId);

        Optional<Note> note = noteRepository.findByUserAndMatch_Id(user, match.getId());
        note.ifPresent(noteRepository::delete);
    }

    public Note updateNoteToMatch(String matchId, String noteId, String note) {
        User user = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.getById(matchId);

        Note noteToUpdate = noteRepository.getById(noteId);
        noteToUpdate.setText(note);

        return noteRepository.save(noteToUpdate);
    }
}
