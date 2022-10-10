package com.leviatan.backend.service;

import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.Note;
import com.leviatan.backend.model.Player;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.MatchRepository;
import com.leviatan.backend.repository.NoteRepository;
import com.leviatan.backend.repository.PlayerRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final SessionUtils sessionUtils;

    private final MatchRepository matchRepository;

    private final PlayerRepository playerRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, SessionUtils sessionUtils, MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.noteRepository = noteRepository;
        this.sessionUtils = sessionUtils;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    public NoteDto addNoteToMatch(String matchId, NoteDto noteDto) {
        User user = sessionUtils.getLoggedUserInfo();
        Match match = matchRepository.getById(matchId);

        Note note = new Note();
        note.setOrganization(user.getOrganization());
        note.setAuthor(user);
        note.setMatch(match);
        fillNoteWithDto(noteDto, note);
        Note savedNote = noteRepository.save(note);
        return savedNote.toDto();
    }

    public void deleteNoteFromMatch(String id) {
        Optional<Note> note = noteRepository.findById(id);
        note.ifPresent(noteRepository::delete);
    }

    public Note updateNoteToMatch(String noteId, NoteDto noteDto) {
        Note noteToUpdate = noteRepository
                .findById(noteId)
                .orElseThrow(() -> new NotFoundException("Note not found"));
        fillNoteWithDto(noteDto, noteToUpdate);
        return noteRepository.save(noteToUpdate);
    }

    private void fillNoteWithDto(NoteDto noteDto, Note note) {
        note.setTopic(noteDto.getTopic());
        note.setContent(noteDto.getContent());
        note.setRelatedChampions(noteDto.getRelatedChampions());
        note.setTeam(noteDto.getTeam());
        note.setTeam(noteDto.getTeam());

        Player player = playerRepository
                .findBySummonerName(noteDto.getSummoner())
                .orElseThrow(() -> new NotFoundException("Player not found"));
        note.setPlayer(player);

        note.setTimestamp(noteDto.getTimestamp());
        note.setIncludesWards(noteDto.getIncludesWards());
        note.setX(noteDto.getX());
        note.setY(noteDto.getY());
    }

    public List<Note> getNotesFromMatch(String matchId) {
        User user = sessionUtils.getLoggedUserInfo();

        return noteRepository.findByOrganization_IdAndMatch_Id(user.getOrganization().getId(), matchId);
    }
}
