package com.leviatan.backend.service;

import com.leviatan.backend.dto.NoteDto;
import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.Match;
import com.leviatan.backend.model.Note;
import com.leviatan.backend.model.Player;
import com.leviatan.backend.model.User;
import com.leviatan.backend.model.league.Champion;
import com.leviatan.backend.repository.MatchRepository;
import com.leviatan.backend.repository.NoteRepository;
import com.leviatan.backend.repository.PlayerRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return noteRepository.save(note).toDto();
    }

    public void deleteNoteFromMatch(String id) {
        Optional<Note> note = noteRepository.findById(id);
        note.ifPresent(noteRepository::delete);
    }

    public NoteDto updateNoteToMatch(String noteId, NoteDto noteDto) {
        Note noteToUpdate = noteRepository
                .findById(noteId)
                .orElseThrow(() -> new NotFoundException("Note not found"));
        fillNoteWithDto(noteDto, noteToUpdate);
        return noteRepository.save(noteToUpdate).toDto();
    }

    private void fillNoteWithDto(NoteDto noteDto, Note note) {
        note.setTopic(noteDto.getTopic());
        note.setContent(noteDto.getContent());
        note.setRelatedChampions(
                noteDto.getRelatedChampions().stream()
                        .map(Champion::findByName)
                        .collect(Collectors.toList())
        );
        note.setTeam(noteDto.getTeam());
        note.setSide(noteDto.getSide());

        if(noteDto.getSummoner() != null) {
            Player player = playerRepository
                    .findBySummonerName(noteDto.getSummoner())
                    .orElse(null);
            note.setPlayer(player);
        } else {
            note.setPlayer(null);
        }

        note.setTimestamp(noteDto.getTimestamp());
        note.setIncludesWards(noteDto.getIncludesWards());
        note.setX(noteDto.getX());
        note.setY(noteDto.getY());
    }

    public List<NoteDto> getNotesFromMatch(String matchId) {
        User user = sessionUtils.getLoggedUserInfo();

        return noteRepository
                .findByOrganization_IdAndMatch_Id(user.getOrganization().getId(), matchId)
                .stream()
                .map(Note::toDto)
                .collect(Collectors.toList());
    }

    public List<NoteDto> getNotes() {
        return noteRepository.findAll().stream().map(Note::toDto).collect(Collectors.toList());
    }

    public List<String> getNoteTopics() {
        return noteRepository.findAll().stream()
                .map(Note::getTopic)
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> getNoteTeams() {
        return noteRepository.findAll().stream()
                .map(Note::getTeam)
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
