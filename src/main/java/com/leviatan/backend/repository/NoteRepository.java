package com.leviatan.backend.repository;

import com.leviatan.backend.model.Note;
import com.leviatan.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, String> {

    List<Note> findByUserAndMatch_Id(User user, String id);
}
