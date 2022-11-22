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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final Integer pageSize = 20;

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

    public void deleteNote(String id) {
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

    public List<NoteDto> getNotes(Integer page,
                                  Optional<String> team,
                                  Optional<String> topic,
                                  Optional<String> search,
                                  Optional<Boolean> flag,
                                  Optional<List<String>> summoner
                                  ) {
        Page<Note> notes = noteRepository.getAllByContentContaining(search.orElse(""), PageRequest.of(page, pageSize));
        return notes.filter(note -> {
            if (team.isPresent() && (note.getTeam() == null || !note.getTeam().equals(team.get()))) return false;
            if (topic.isPresent() && (note.getTopic() == null || !note.getTopic().equals(topic.get()))) return false;
            if (summoner.isPresent() && ((note.getPlayer() == null || note.getPlayer().getSummonerName() == null) || !summoner.get().contains(note.getPlayer().getSummonerName()))) return false;
            if (flag.isPresent() && flag.get() && (note.getFlagged() == null || !note.getFlagged())) return false;
            return true;
        }).stream().map(Note::toDto).collect(Collectors.toList());
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

    public List<String> getNoteSummoners() {
        return noteRepository.findAll().stream()
                .map(note -> {
                    if (note.getPlayer() != null) return note.getPlayer().getSummonerName();
                    else return null;
                })
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Note flagNote(String noteId, Boolean flag) {
        Optional<Note> selectedNote = noteRepository.findById(noteId);
        if (selectedNote.isPresent()) {
            Note presentNote = selectedNote.get();
            presentNote.setFlagged(flag);
            noteRepository.save(presentNote);
            return presentNote;
        } else {
            throw new NoSuchElementException();
        }
    }
}
